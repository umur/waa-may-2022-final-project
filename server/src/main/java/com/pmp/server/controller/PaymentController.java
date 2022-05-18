package com.pmp.server.controller;

import com.pmp.server.domain.Property;
import com.pmp.server.domain.PropertyRentalHistory;
import com.pmp.server.domain.Transaction;
import com.pmp.server.dto.NotificationDTO;
import com.pmp.server.dto.payment.CheckoutSessionDTO;
import com.pmp.server.dto.payment.CheckoutSessionResponseDTO;
import com.pmp.server.dto.payment.TransactionDTO;
import com.pmp.server.service.PaymentService;
import com.pmp.server.service.PropertyRentalHistoryService;
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
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin
public class PaymentController {
    private final SimpMessagingTemplate template;
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    public PaymentController(SimpMessagingTemplate template, PropertyService propertyService, TransactionService transactionService, PaymentService paymentService, PropertyRentalHistoryService propertyRentalHistoryService) {
        this.template = template;
        this.propertyService = propertyService;
        this.transactionService = transactionService;
        this.paymentService = paymentService;
        this.propertyRentalHistoryService = propertyRentalHistoryService;
    }

    private final PropertyService propertyService;
    private final TransactionService transactionService;
    private final PaymentService paymentService;

    private final PropertyRentalHistoryService propertyRentalHistoryService;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<CheckoutSessionResponseDTO> createCheckoutSession(@RequestBody CheckoutSessionDTO body) throws StripeException {

        var session = paymentService.stripeCheckout(body);


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
