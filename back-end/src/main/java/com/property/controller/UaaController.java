package com.property.controller;

import com.property.dto.LoginRequest;
import com.property.dto.UserRegistration;
import com.property.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UaaController {

    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        var loginResponse = userService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserRegistration> signup(@RequestBody UserRegistration userRegistration){
        UserRegistration registration = userService.save(userRegistration);
        return ResponseEntity.ok(registration);
    }
}
