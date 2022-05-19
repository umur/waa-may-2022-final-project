package com.project.accessdenied.controller;

import com.project.accessdenied.entity.User;
import com.project.accessdenied.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable long id) {
        return userService.getById(id);
    }

    @PostMapping
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        userService.deleteById(id);
    }
}
