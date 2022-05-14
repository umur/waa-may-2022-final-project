package com.pmp.server.repo;

import com.pmp.server.domain.Property;
import com.pmp.server.domain.PropertyRentalHistory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PropertyRentalHistoryRepo  extends PagingAndSortingRepository<PropertyRentalHistory, UUID> {
}
