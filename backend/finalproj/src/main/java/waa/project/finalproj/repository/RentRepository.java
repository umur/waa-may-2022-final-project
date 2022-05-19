package waa.project.finalproj.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import waa.project.finalproj.entity.Rent;

import java.util.List;

public interface RentRepository extends CrudRepository<Rent, Integer> {

}
