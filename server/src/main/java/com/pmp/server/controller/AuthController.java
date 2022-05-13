package com.pmp.server.controller;

import com.pmp.server.dto.LoginDTO;
import com.pmp.server.dto.UserDTO;
import com.pmp.server.security.service.impl.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
  private final AuthServiceImpl authService;

  public AuthController(AuthServiceImpl authService) {
    this.authService = authService;
  }

  @PostMapping("register")
  public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
    UserDTO user = authService.registerUser(userDTO);
    return ResponseEntity.ok(user);
  }

  @PostMapping("login")
  public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
    return ResponseEntity.ok(authService.login(loginDTO));
  }
}
