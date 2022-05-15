package com.property.controller;

import com.property.domain.Role;
import com.property.dto.request.LoginRequest;
import com.property.dto.request.UserRegistrationRequest;
import com.property.dto.response.UserRegistrationResponse;
import com.property.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/uaa")
public class UaaController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        var loginResponse = userService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserRegistrationResponse> signup(@RequestBody UserRegistrationRequest userRegistration){
        UserRegistrationResponse registration = userService.save(userRegistration);
        return ResponseEntity.ok(registration);
    }

//    @GetMapping("/useractivedeactive")
//    public void userActiveDeactive(@RequestParam long id, @RequestParam Role role){
//        userService.userActive(id, role);
//    }
}
