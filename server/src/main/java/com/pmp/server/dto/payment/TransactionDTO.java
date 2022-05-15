package com.pmp.server.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private String productId;

    private String priceId;

    private UUID propertyRentalHistoryId;
}
