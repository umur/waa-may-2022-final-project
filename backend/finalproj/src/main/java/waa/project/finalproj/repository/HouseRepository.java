package waa.project.finalproj.repository;

import org.springframework.data.repository.CrudRepository;
import waa.project.finalproj.entity.Property;

import java.util.List;

public interface HouseRepository extends CrudRepository<Property, Integer> {
    List<Property> findAllByDeletedAtIsNull();
}
