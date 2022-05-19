package com.project.accessdenied.service.impl;

import com.project.accessdenied.entity.User;
import com.project.accessdenied.repository.UserRepository;
import com.project.accessdenied.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        var result = new ArrayList<User>();
        userRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
