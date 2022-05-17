package com.pmp.server.repo;

import com.pmp.server.domain.Property;
import com.pmp.server.domain.PropertyRentalHistory;
import com.pmp.server.domain.User;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface PropertyRentalHistoryRepo  extends PagingAndSortingRepository<PropertyRentalHistory, UUID> {

    List<PropertyRentalHistory> findAllByRentedBy(User user);

    List<PropertyRentalHistory> findAllByPropertyOwnedBy(User user);
  List<PropertyRentalHistory> findByPropertyId(UUID uuid);


    @Query(value = "SELECT * \n" +
            "FROM property_rental_history as e \n" +
            "WHERE e.end_date BETWEEN :startDate AND :endDate",
    nativeQuery = true)
    List<PropertyRentalHistory> findAllByEndDateBefore(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
