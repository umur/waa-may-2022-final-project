package waa.project.finalproj.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.project.finalproj.entity.PropertyType;

import java.util.List;

@Repository
public interface PropertyTypeRepository extends CrudRepository<PropertyType, Integer> {
    List<PropertyType> findAllByDeletedAtIsNull();
}
