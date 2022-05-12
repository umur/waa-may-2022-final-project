package waa.propertymanagementbackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.propertymanagementbackend.domain.Users;

@Repository
public interface UserRepository extends CrudRepository<Users,Integer> {
}
