package waa.project.finalproj.repository;

import org.springframework.data.repository.CrudRepository;
import waa.project.finalproj.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
