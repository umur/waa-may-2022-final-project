package com.pmp.server.repo;

import com.pmp.server.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User, UUID> {
    Page<User> findAllByRoleId(Pageable pageable, @Param("role_id") UUID roleId);
    User findByEmail(String email);

    @Query(value = "SELECT * FROM USERS u WHERE (lower(u.first_name) like lower(concat('%',:keywords,'%')) OR lower(u.last_name) like lower(concat('%',:keywords,'%')) OR lower(u.email) like lower(concat('%',:keywords,'%'))) AND u.role_id = :roleId",
            countQuery = "SELECT count(u) FROM USERS u WHERE (lower(u.first_name) like lower(concat('%',:keywords,'%')) OR lower(u.last_name) like lower(concat('%',:keywords,'%')) OR lower(u.email) like lower(concat('%',:keywords,'%'))) AND u.role_id = :roleId",
    nativeQuery = true)
    Page<User> findAllWithJPQL(@Param("roleId") UUID roleId, @Param("keywords") String keywords, Pageable pageable);
}
