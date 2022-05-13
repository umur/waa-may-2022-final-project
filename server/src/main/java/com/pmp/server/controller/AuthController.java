package com.pmp.server.controller;

import com.pmp.server.dto.LoginDTO;
import com.pmp.server.dto.ResetPasswordDTO;
import com.pmp.server.dto.UpdateUserDTO;
import com.pmp.server.dto.UserDTO;
import com.pmp.server.dto.common.ResponseMessage;
import com.pmp.server.security.service.impl.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/auth")
public class AuthController {
  private final AuthServiceImpl authService;

  public AuthController(AuthServiceImpl authService) {
    this.authService = authService;
  }

  @PostMapping("register")
  public ResponseMessage registerUser(@RequestBody UserDTO userDTO){
    return authService.registerUser(userDTO);
  }

  @PostMapping("login")
  public ResponseMessage login(@RequestBody LoginDTO loginDTO) {
    return authService.login(loginDTO);
  }

  @PostMapping("update")
  public ResponseMessage update(@PathVariable UUID id, @RequestBody UpdateUserDTO updateUserDTO) {
    return authService.updateUser(id, updateUserDTO);
  }

  @PostMapping("reset")
  public ResponseMessage reset(@PathVariable UUID id, @RequestBody ResetPasswordDTO resetPasswordDTO) {
    return authService.resetPassword(id,resetPasswordDTO );
  }
}
