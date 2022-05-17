package com.property.respository;

import com.property.domain.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends CrudRepository<Property, Long> {

    List<Property> findAllByPropertyTypeContains(@Param("propertyType") String propertyType);

    List<Property> findAllByNoOfBedRoom(int noOfBedRoom);

    List<Property> findAllByAddress_StateAndAddress_City(String state, String city);

    List<Property> findAllByAddress_State(String state);

    List<Property> findAllByAddress_City(String city);
}
