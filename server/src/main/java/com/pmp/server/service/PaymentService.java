package com.pmp.server.service;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

public interface PaymentService {
    void handleSessionSucceeded(Session session) throws StripeException;
}
