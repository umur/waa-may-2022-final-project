package com.project.accessdenied.controller;

import com.project.accessdenied.component.StripeClient;
import com.project.accessdenied.dto.PaymentDto;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/payment")
public class PaymentController {

    private final StripeClient stripeClient;

    public PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @PostMapping
    public Charge chargeCard(@RequestBody PaymentDto paymentDto) throws APIConnectionException, APIException, AuthenticationException, InvalidRequestException, CardException {
        return stripeClient.chargeCreditCard(paymentDto.getToken(), paymentDto.getAmount());
    }
}
