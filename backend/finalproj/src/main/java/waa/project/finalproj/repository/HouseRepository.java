package waa.project.finalproj.repository;

import org.springframework.data.repository.CrudRepository;
import waa.project.finalproj.model.House;

public interface HouseRepository extends CrudRepository<House, Integer> {
}
