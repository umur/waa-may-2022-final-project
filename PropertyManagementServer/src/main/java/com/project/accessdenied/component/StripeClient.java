package com.project.accessdenied.component;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StripeClient {

    @Autowired
    StripeClient() {
        Stripe.apiKey="sk_test_51L0IFfLni6USJ4En1glDlYDg31ppBozga0EhmsEHDNDdqCkoby9wDsQgu6giIZjo3D5IFHc0QgPXjtlabtGRIgLy00640RkSlj";
    }

    public Charge chargeCreditCard(String token, int amount) throws APIConnectionException, APIException, AuthenticationException, InvalidRequestException, CardException {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", amount * 100);
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }
}
