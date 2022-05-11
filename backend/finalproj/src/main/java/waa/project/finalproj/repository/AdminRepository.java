package waa.project.finalproj.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.project.finalproj.model.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {
}
