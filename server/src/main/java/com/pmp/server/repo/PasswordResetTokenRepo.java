package com.pmp.server.repo;

import com.pmp.server.domain.PasswordResetToken;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PasswordResetTokenRepo extends CrudRepository<PasswordResetToken, UUID> {
  PasswordResetToken findByToken(String token);
}
