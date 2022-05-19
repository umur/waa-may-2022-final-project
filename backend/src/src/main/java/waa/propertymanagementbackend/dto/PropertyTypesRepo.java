package waa.propertymanagementbackend.dto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.propertymanagementbackend.domain.PropertyType;

@Repository
public interface PropertyTypesRepo extends CrudRepository <PropertyType,Integer> {
}
