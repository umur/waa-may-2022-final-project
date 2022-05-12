package waa.propertymanagementbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.propertymanagementbackend.domain.Property;

@Repository
public interface PropertyRepository extends CrudRepository<Property,Integer> {
}
