package com.pmp.server.security.service;

import com.pmp.server.dto.LoginDTO;
import com.pmp.server.dto.UserDTO;
import com.pmp.server.dto.common.ResponseMessage;
import org.keycloak.representations.AccessTokenResponse;

public interface AuthService {
  ResponseMessage login(LoginDTO loginDTO);
  ResponseMessage registerUser(LoginDTO userDTO);
}
