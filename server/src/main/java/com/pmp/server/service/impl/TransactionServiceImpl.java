package com.pmp.server.service.impl;

import com.pmp.server.domain.PropertyRentalHistory;
import com.pmp.server.domain.Transaction;
import com.pmp.server.dto.payment.TransactionDTO;
import com.pmp.server.exceptionHandler.exceptions.CustomErrorException;
import com.pmp.server.repo.PropertyRentalHistoryRepo;
import com.pmp.server.repo.TransactionRepo;
import com.pmp.server.repo.PropertyRepo;
import com.pmp.server.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.pmp.server.utils.constants.ResponseMessageConstants.*;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepo transactionRepo;
    private PropertyRepo propertyRepo;

    private PropertyRentalHistoryRepo propertyRentalHistoryRepo;

    public Transaction findByPropertyId(UUID propertyId) {
        return transactionRepo.findByPropertyRentalHistoryId(propertyId);
    }

    @Override
    public Transaction save(TransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID());
        transaction.setProductId(dto.getProductId());

        // Get Property object
        Optional<PropertyRentalHistory> rent = propertyRentalHistoryRepo.findById(dto.getPropertyRentalHistoryId());
        if (rent.isPresent() == false) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, DATA_NOT_FOUND, dto);
        } else {
            transaction.setPropertyRentalHistory(rent.get());
            rent.get().setTransaction(transaction);
        }

        return transactionRepo.save(transaction);
    }
}
