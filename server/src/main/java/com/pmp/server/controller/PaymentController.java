package com.pmp.server.controller;

import com.pmp.server.dto.PaymentConfigDTO;
import com.pmp.server.dto.PaymentDTO;
import com.pmp.server.dto.PaymentRequestDTO;
import com.pmp.server.dto.common.ResponseMessage;
import com.pmp.server.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Value("${stripe.public.key}")
    private String stripePublicKey;

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/config")
    public ResponseMessage getConfig() {
        return new ResponseMessage("success", HttpStatus.OK, new PaymentConfigDTO(stripePublicKey));
    }

    @PostMapping("/create-payment-intent")
    public ResponseMessage createPaymentIntent(@RequestBody PaymentRequestDTO postBody) throws StripeException {
        PaymentIntentCreateParams.Builder paramsBuilder = new PaymentIntentCreateParams
                .Builder()
                .addPaymentMethodType(postBody.getPaymentMethodType())
                .setCurrency(postBody.getCurrency())
                .setReceiptEmail(postBody.getEmail())
                // TODO: Verify your integration in this guide by including this parameter
                .putMetadata("integration_check", "accept_a_payment")
                .setAmount(postBody.getAmount());

        log.info("Payment method type:", postBody.getPaymentMethodType());

        PaymentIntentCreateParams createParams = paramsBuilder.build();

        // Create a PaymentIntent with the order amount and currency
        PaymentIntent intent = PaymentIntent.create(createParams);

        // Send PaymentIntent details to client
        return new ResponseMessage("success", HttpStatus.CREATED, new PaymentDTO(intent.getClientSecret()));
    }
}
