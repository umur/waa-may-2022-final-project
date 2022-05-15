package com.pmp.server.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutSessionDTO {
    private UUID propertyRentalHistoryId;
    private UUID propertyId;
    private Long numberOfDays;
}
