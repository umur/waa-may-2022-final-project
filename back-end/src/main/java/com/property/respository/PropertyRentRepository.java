package com.property.respository;

import com.property.domain.PropertyRent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PropertyRentRepository extends CrudRepository<PropertyRent, Long> {

    Optional<PropertyRent> findByPropertyIdAndRentEndDateIsAfter(Long id, LocalDate date);
}
