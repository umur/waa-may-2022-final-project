package com.project.accessdenied.repository;

import com.project.accessdenied.entity.PropertyType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyTypeRepository extends CrudRepository<PropertyType, Long> {
}
