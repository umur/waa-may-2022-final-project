package com.pmp.server.service;

import com.pmp.server.dto.payment.CheckoutSessionDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

public interface PaymentService {
    void handleSessionSucceeded(Session session) throws StripeException;

    Session stripeCheckout(CheckoutSessionDTO body) throws StripeException;
}
