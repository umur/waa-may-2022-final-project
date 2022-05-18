package com.pmp.server.service.impl;

import com.pmp.server.domain.Property;
import com.pmp.server.dto.NotificationDTO;
import com.pmp.server.dto.payment.UpdateTransactionDTO;
import com.pmp.server.service.PaymentService;
import com.pmp.server.service.TransactionService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.ChargeCollection;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;
    private final PropertyServiceImpl propertyService;

    private final SimpMessagingTemplate template;

    public PaymentServiceImpl(PropertyServiceImpl propertyService, SimpMessagingTemplate template, TransactionService transactionService) {
        this.propertyService = propertyService;
        this.template = template;
        this.transactionService = transactionService;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final TransactionService transactionService;

    @Override
    public void handleSessionSucceeded(Session session) throws StripeException {
        // Get payment intent
        String paymentIntentId = session.getPaymentIntent();

        PaymentIntent paymentIntent =
                PaymentIntent.retrieve(
                        paymentIntentId
                );


        // Get charge from payment intent
        ChargeCollection charges = paymentIntent.getCharges();
        Charge charge = charges.getData().stream().findFirst().get();

        log.warn("Charge {} - {} - receipt: {}", charge.getId(), charge.getAmountCaptured(), charge.getReceiptUrl() );

        // Get metadata
        Map<String, String> metadata = session.getMetadata();
        UUID propertyId = UUID.fromString(metadata.get("property_id"));
        UUID rentalPropertyHistoryId = UUID.fromString(metadata.get("rental_property_history_id"));
        UUID transactionId = UUID.fromString(metadata.get("transaction_id"));

        // Get transaction
        UpdateTransactionDTO dto = new UpdateTransactionDTO();
        dto.setId(transactionId);
        dto.setTransactionId(charge.getId());
        dto.setStatus(charge.getStatus());
        dto.setReceiptUrl(charge.getReceiptUrl());
        transactionService.update(dto);

        Property pty = propertyService.getById(propertyId);

        this.template.convertAndSend("/topic/landlords", new NotificationDTO(pty.getOwnedBy().getId().toString(),"Your property has been rented!"));
    }
}
