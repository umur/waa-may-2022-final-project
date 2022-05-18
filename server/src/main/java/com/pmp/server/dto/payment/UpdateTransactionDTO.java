package com.pmp.server.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTransactionDTO {
    private UUID id;

    private String transactionId;

    private String status;

    private String receiptUrl;
}
