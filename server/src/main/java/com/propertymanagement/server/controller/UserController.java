package com.propertymanagement.server.controller;

import com.propertymanagement.server.dto.UserRespDto;
import com.propertymanagement.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserRespDto>> getAllUsers(){
        List<UserRespDto> userRespDtos = userService.findAll();
        return ResponseEntity.ok(userRespDtos);
    }

    @GetMapping("/10-most-recent-tenants")
    public ResponseEntity<List<UserRespDto>> find10MostRecentTenants(){
        List<UserRespDto> recentTenants = userService.find10MostRecentTenants();
        return ResponseEntity.ok(recentTenants);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<UserRespDto>> deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
