package waa.project.finalproj.repository;

import org.springframework.data.repository.CrudRepository;
import waa.project.finalproj.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
