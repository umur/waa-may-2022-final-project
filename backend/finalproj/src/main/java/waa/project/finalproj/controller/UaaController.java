package waa.project.finalproj.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.project.finalproj.model.LoginRequest;
import waa.project.finalproj.service.UaaService;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin("*")
@AllArgsConstructor
public class UaaController {

    private final UaaService uaaService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        var loginResponse = uaaService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }
}