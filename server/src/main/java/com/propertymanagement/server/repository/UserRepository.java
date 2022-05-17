package com.propertymanagement.server.repository;

import com.propertymanagement.server.domain.Role;
import com.propertymanagement.server.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    List<User> findTop10ByRoleEqualsOrderByRegisterTimeDesc(Role role);
}
