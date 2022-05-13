package com.pmp.server.security.service;

import com.pmp.server.dto.LoginDTO;
import com.pmp.server.dto.UserDTO;
import org.keycloak.representations.AccessTokenResponse;

public interface AuthService {
  AccessTokenResponse login(LoginDTO loginDTO);
  UserDTO registerUser(LoginDTO userDTO);
}
