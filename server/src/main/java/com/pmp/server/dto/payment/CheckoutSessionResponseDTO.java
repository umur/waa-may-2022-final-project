package com.pmp.server.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutSessionResponseDTO {
    private String sessionId;

    private String url;
}
