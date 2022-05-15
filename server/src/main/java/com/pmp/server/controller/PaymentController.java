package com.pmp.server.controller;

import com.pmp.server.domain.Property;
import com.pmp.server.domain.Transaction;
import com.pmp.server.dto.PaymentConfigDTO;
import com.pmp.server.dto.PaymentDTO;
import com.pmp.server.dto.PaymentRequestDTO;
import com.pmp.server.dto.common.ResponseMessage;
import com.pmp.server.dto.payment.CheckoutSessionDTO;
import com.pmp.server.dto.payment.CheckoutSessionResponseDTO;
import com.pmp.server.dto.payment.TransactionDTO;
import com.pmp.server.service.TransactionService;
import com.pmp.server.service.PropertyService;
import com.pmp.server.utils.enums.ECurrency;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Price;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

    @Value("${stripe.public.key}")
    private String stripePublicKey;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    public PaymentController(PropertyService propertyService, TransactionService transactionService) {
        this.propertyService = propertyService;
        this.transactionService = transactionService;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

//    private final PaymentService paymentService;

    private final PropertyService propertyService;
    private final TransactionService transactionService;

//    @GetMapping("/config")
//    public ResponseMessage getConfig() {
//        return new ResponseMessage("success", HttpStatus.OK, new PaymentConfigDTO(stripePublicKey));
//    }
//
//    @PostMapping("/create-payment-intent")
//    public ResponseMessage createPaymentIntent(@RequestBody PaymentRequestDTO postBody) throws StripeException {
//        PaymentIntentCreateParams.Builder paramsBuilder = new PaymentIntentCreateParams
//                .Builder()
//                .addPaymentMethodType(postBody.getPaymentMethodType())
//                .setCurrency(postBody.getCurrency())
//                .setReceiptEmail(postBody.getEmail())
//                // TODO: Verify your integration in this guide by including this parameter
//                .putMetadata("integration_check", "accept_a_payment")
//                .setAmount(postBody.getAmount());
//
//        log.info("Payment method type:", postBody.getPaymentMethodType());
//
//        PaymentIntentCreateParams createParams = paramsBuilder.build();
//
//        // Create a PaymentIntent with the order amount and currency
//        PaymentIntent intent = PaymentIntent.create(createParams);
//
//        // Send PaymentIntent details to client
//        return new ResponseMessage("success", HttpStatus.CREATED, new PaymentDTO(intent.getClientSecret()));
//    }

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
            transactionDTO.setPropertyRentalHistoryId(property.getId());

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

//        // Metadata
//        Map<String, String> initialMetadata = new HashMap<>();
//        initialMetadata.put("order_id", "6735");
//        params.put("metadata", initialMetadata);


        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setClientReferenceId("client_reference_id")
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(clientDomain + "/payment/success")
                        .setCancelUrl(clientDomain + "/payment/canceled")
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(body.getNumberOfDays())
                                        // Set price id
                                        .setPrice(transaction.getPriceId())
                                        .setAmount(Double.valueOf(property.getRentAmount()).longValue() * 100)
                                        .setCurrency(currency)
                                        .setName(property.getPropertyName())
                                        .build())
                        .build();
        Session session = Session.create(params);

        CheckoutSessionResponseDTO dto = new CheckoutSessionResponseDTO();
        dto.setSessionId(session.getId());
        dto.setUrl(session.getUrl());

        return ResponseEntity.ok(dto);
    }
}
