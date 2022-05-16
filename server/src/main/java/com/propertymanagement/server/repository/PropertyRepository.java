package com.propertymanagement.server.repository;

import com.propertymanagement.server.domain.Property;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<Property, Long> {
}
