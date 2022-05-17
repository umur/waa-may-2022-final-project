package com.propertymanagement.server.repository;

import com.propertymanagement.server.domain.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {
    PasswordResetToken findByUserId(Long userId);
    PasswordResetToken findByToken(String token);
}
