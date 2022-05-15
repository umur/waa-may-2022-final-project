package com.pmp.server.controller;

import com.pmp.server.domain.Property;
import com.pmp.server.domain.Transaction;
import com.pmp.server.dto.payment.CheckoutSessionDTO;
import com.pmp.server.dto.payment.CheckoutSessionResponseDTO;
import com.pmp.server.dto.payment.TransactionDTO;
import com.pmp.server.service.PaymentService;
import com.pmp.server.service.TransactionService;
import com.pmp.server.service.PropertyService;
import com.pmp.server.utils.enums.ECurrency;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    public PaymentController(PropertyService propertyService, TransactionService transactionService, PaymentService paymentService) {
        this.propertyService = propertyService;
        this.transactionService = transactionService;
        this.paymentService = paymentService;
    }

    private final PropertyService propertyService;
    private final TransactionService transactionService;
    private final PaymentService paymentService;

    @Value("${client.domain}")
    private String clientDomain;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<CheckoutSessionResponseDTO> createCheckoutSession(@RequestBody CheckoutSessionDTO body) throws StripeException {
        Property property = propertyService.getById(body.getPropertyId());
        String currency = ECurrency.USD.getValue();

        // Save payment transaction into database
        Transaction transaction = transactionService.findByPropertyId(property.getId());
        if (transaction == null) {
            // Create a product
            Map<String, Object> params = new HashMap<>();
            params.put("name", property.getPropertyName());
            params.put("description", property.getDescription());
            com.stripe.model.Product product = com.stripe.model.Product.create(params);

            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setProductId(product.getId());
            transactionDTO.setPropertyRentalHistoryId(body.getPropertyRentalHistoryId());

            // Create price id for product
            Map<String, Object> priceParams = new HashMap<>();
            priceParams.put("unit_amount", Double.valueOf(property.getRentAmount()).longValue() * 100L);
            priceParams.put("currency", currency);
            priceParams.put("product", product.getId());
            Price price = Price.create(priceParams);

            // Set price id
            transactionDTO.setPriceId(price.getId());

            // Save transaction
            transaction = transactionService.save(transactionDTO);
        }

        // Metadata
        Map<String, String> initialMetadata = new HashMap<>();
        initialMetadata.put("property_id", property.getId().toString());
        initialMetadata.put("rental_property_history_id", transaction.getPropertyRentalHistory().getId().toString());
        initialMetadata.put("transaction_id", transaction.getId().toString());

        String paymentURL = clientDomain + "/payment/" + body.getPropertyRentalHistoryId();

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .putAllMetadata(initialMetadata)
                        .setSuccessUrl(paymentURL + "/success=true?session_id={CHECKOUT_SESSION_ID}")
                        .setCancelUrl(paymentURL + "/canceled")
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(body.getNumberOfDays())
                                        // Set price id
                                        .setPrice(transaction.getPriceId())
                                        .build())
                        .build();
        Session session = Session.create(params);
        session.setMetadata(initialMetadata);

        CheckoutSessionResponseDTO dto = new CheckoutSessionResponseDTO();
        dto.setSessionId(session.getId());
        dto.setUrl(session.getUrl());

        return ResponseEntity.ok(dto);
    }

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    @PostMapping("/webhook")
    public String handleStripeEvent(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) throws StripeException {
        if (sigHeader == null) return "";

        Event event;

        // Only verify the event if you have an endpoint secret defined.
        // Otherwise, use the basic event deserialized with GSON.
        try {
            event = Webhook.constructEvent(
                    payload, sigHeader, endpointSecret
            );
        } catch (SignatureVerificationException e) {
            // Invalid signature
            log.info("⚠️  Webhook error while validating signature.");
            return "";
        }

        // Deserialize the nested object inside the event
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            // Deserialization failed, probably due to an API version mismatch.
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
        }

        // Handle the event
        switch (event.getType()) {
            case "checkout.session.completed":
                Session session = (Session) stripeObject;
                log.warn("Checkout session: ");
                paymentService.handleSessionSucceeded(session);
            default:
                log.error("Unhandled event type: {}", event.getType());
                break;
        }

        return "";
    }
}
