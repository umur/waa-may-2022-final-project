package com.propertymanagement.server.repository;

import com.propertymanagement.server.domain.RentalActivity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface RentalActivityRepository extends CrudRepository<RentalActivity, Long> {

    Optional<RentalActivity> findByPropertyIdAndEndDateIsAfter(Long id, LocalDate date);
}
