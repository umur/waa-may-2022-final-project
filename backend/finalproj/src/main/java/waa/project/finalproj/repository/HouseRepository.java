package waa.project.finalproj.repository;

import org.springframework.data.repository.CrudRepository;
import waa.project.finalproj.entity.House;

import java.util.List;

public interface HouseRepository extends CrudRepository<House, Integer> {
    List<House> findAllByDeletedAtIsNull();
}
