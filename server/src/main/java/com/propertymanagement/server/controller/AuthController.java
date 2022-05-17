package com.propertymanagement.server.controller;

import com.propertymanagement.server.domain.PasswordDto;
import com.propertymanagement.server.dto.LoginDto;
import com.propertymanagement.server.dto.UserDto;
import com.propertymanagement.server.dto.UserRespDto;
import com.propertymanagement.server.service.AuthService;
import com.propertymanagement.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserRespDto> signup(@RequestBody UserDto userDto) {
        UserRespDto userRespDto = userService.save(userDto);
        return ResponseEntity.ok(userRespDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        var loginResponse = authService.login(loginDto);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PutMapping("/user-active/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void userActivate(@PathVariable long id) {
        userService.activateUser(id);
    }

    @PutMapping("/user-deactive/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void userDectivate(@PathVariable long id) {
        userService.deActivateUser(id);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> sendForgotPasswordEmail(@PathVariable String email) {
        authService.sendForgotPasswordEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<UserRespDto> resetPassword(@RequestBody PasswordDto PasswordDto, @RequestParam String token) {
        UserRespDto res = authService.resetPassword(PasswordDto, token);
        return ResponseEntity.ok(res);
    }

}
