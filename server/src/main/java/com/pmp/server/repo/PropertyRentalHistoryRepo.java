package com.pmp.server.repo;

import com.pmp.server.domain.Property;
import com.pmp.server.domain.PropertyRentalHistory;
import com.pmp.server.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PropertyRentalHistoryRepo  extends PagingAndSortingRepository<PropertyRentalHistory, UUID> {
    List<PropertyRentalHistory> findAllByRentedBy(User user);
}
