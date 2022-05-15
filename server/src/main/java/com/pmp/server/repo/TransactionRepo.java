package com.pmp.server.repo;

import com.pmp.server.domain.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepo extends PagingAndSortingRepository<Transaction, UUID> {
    Transaction findByPropertyRentalHistoryId(@Param("property_rental_history_id") UUID propertyRentalHistoryId);
}