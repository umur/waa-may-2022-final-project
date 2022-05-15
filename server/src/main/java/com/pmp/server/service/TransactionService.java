package com.pmp.server.service;

import com.pmp.server.domain.Transaction;
import com.pmp.server.dto.payment.TransactionDTO;

import java.util.UUID;

public interface TransactionService {
    Transaction findByPropertyId(UUID propertyId);

    Transaction save(TransactionDTO dto);
}
