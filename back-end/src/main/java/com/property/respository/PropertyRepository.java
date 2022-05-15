package com.property.respository;

import com.property.domain.Property;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PropertyRepository extends CrudRepository<Property, Long> {

    List<Property> findAllByPropertyTypeContains(String name);

    List<Property> findAllByNoOfBedRoom(int noOfBedRoom);

    //Where is the connection between address and property.
}
