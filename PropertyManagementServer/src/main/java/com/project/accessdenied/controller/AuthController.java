package com.project.accessdenied.controller;

import com.project.accessdenied.dto.ResponseDto;
import com.project.accessdenied.dto.UserRegisterDto;
import com.project.accessdenied.model.LoginRequest;
import com.project.accessdenied.service.impl.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseDto register(@RequestBody UserRegisterDto userRegisterDto) throws Exception {
        return authService.register(userRegisterDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        var loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }
}
