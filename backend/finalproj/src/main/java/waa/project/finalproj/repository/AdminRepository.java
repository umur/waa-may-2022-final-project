package waa.project.finalproj.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.project.finalproj.entity.Admin;

import java.util.List;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {

    List<Admin> findAllByDeletedAtIsNull();
}
