package waa.project.finalproj.repository;

import org.springframework.data.repository.CrudRepository;
import waa.project.finalproj.entity.Landlord;

import java.util.List;

public interface LandlordRepository extends CrudRepository<Landlord, Integer> {

    List<Landlord> findAllByDeletedAtIsNull();
}
