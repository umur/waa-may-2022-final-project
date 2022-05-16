package waa.propertymanagementbackend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import waa.propertymanagementbackend.domain.PropertyRentHistory;
import waa.propertymanagementbackend.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    @Query(value = "select * from users where  active=true AND deleted =false\n" +
            "            order by last_logged_in_at desc limit 10",
            nativeQuery = true)
    List<User> getLastRecentTenants();

    User findByEmail(String email);

    List<User> findByRoleDescription(String roleName);

}
