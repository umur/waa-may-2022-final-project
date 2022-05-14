package waa.project.finalproj.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.project.finalproj.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findAllByDeletedAtIsNull();
    Optional<User> findByEmail(String email);


}
