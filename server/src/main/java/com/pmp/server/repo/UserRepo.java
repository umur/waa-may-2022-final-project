package com.pmp.server.repo;

import com.pmp.server.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User, UUID> {
}
