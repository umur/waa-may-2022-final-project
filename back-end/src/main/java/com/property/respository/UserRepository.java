package com.property.respository;

import com.property.domain.Role;
import com.property.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

//    void userActive(long id, Role role);
}
