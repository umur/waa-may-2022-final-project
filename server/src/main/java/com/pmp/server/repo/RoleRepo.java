package com.pmp.server.repo;

import com.pmp.server.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepo extends PagingAndSortingRepository<Role, UUID> {
    Role findByRoleName(@Param("roleName") String name);
}
