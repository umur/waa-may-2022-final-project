package com.property.respository;

import com.property.domain.Property;
import com.property.dto.response.PropertyRequestDateDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface PropertyRepository extends CrudRepository<Property, Long> {

    List<Property> findAllByPropertyTypeContains(String name);

    List<Property> findAllByDeleteIsFalse();

    List<Property> findAllByNoOfBedRoom(int noOfBedRoom);

    List<Property> findAllByAvailableIsTrue();


    @Query("from Property p inner join PropertyRent pr on p.id=pr.property.id where p.landLord.id=:id and p.delete=false and pr.rentEndDate>=:now and pr.rentEndDate<=:then")
    List<Property> findAllLeaseByLandLordAndDateRange(@Param("id") Long id, @Param("now") LocalDate now, @Param("then") LocalDate then, Pageable pageable);


    @Query("select new com.property.dto.response.PropertyRequestDateDto(pr.createdAt,p.id) from Property p inner join PropertyRent pr on p.id=pr.property.id where p.delete=false and pr.createdAt>=:previous and pr.createdAt<=:now")
    Set<PropertyRequestDateDto> findAllByRentedDateRange(@Param("previous") LocalDate previous, @Param("now") LocalDate now);


    @Query("from Property p inner join PropertyRent pr on p.id=pr.property.id and pr.rentEndDate>=:now and p.delete=false")
    List<Property> findAllRentedByDateRange(@Param("now") LocalDate now, Pageable pageable);


    List<Property> findAllByAddress_StateAndAddress_City(String state, String city);

    List<Property> findAllByAddress_State(String state);

    List<Property> findAllByAddress_City(String city);
}
