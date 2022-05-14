package com.pmp.server.dto.common;

import com.pmp.server.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenWithUserDTO {
  public User user;
  public AccessTokenResponse tokenResponse;
}
