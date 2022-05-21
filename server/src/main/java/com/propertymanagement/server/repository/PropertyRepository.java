package com.propertymanagement.server.repository;

import com.propertymanagement.server.domain.Property;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface PropertyRepository extends CrudRepository<Property, Long> {

    List<Property> findAllByState(String state);

    List<Property> findAllByNumberOfBedrooms(int noOfBedRoom);

    List<Property> findAllByIsOccupiedIsTrue();

    @Query("from Property p inner join RentalActivity ra on p.id=ra.property.id where p.ownedBy.id=:id and p.delete=false and ra.endDate>=:now and ra.endDate<=:then")
    List<Property> get10PropertiesLeaseEndInAMonth(@Param("id") Long id, @Param("now") LocalDate now, @Param("then") LocalDate then, Pageable pageable);


    @Query("from Property p inner join RentalActivity ra on p.id=ra.property.id and ra.endDate>=:now and p.delete=false")
    List<Property> getLast10PropertiesRented(@Param("now") LocalDate now, Pageable pageable);




}
