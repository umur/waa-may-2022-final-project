package com.project.accessdenied.service;

import com.project.accessdenied.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAll();
    User getById(long id);
    void save(User user);
    void deleteById(long id);
    User getByEmail(String email);
}
