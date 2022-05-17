package com.propertymanagement.server.service.impl;

import com.propertymanagement.server.domain.Role;
import com.propertymanagement.server.domain.User;
import com.propertymanagement.server.dto.UserDto;
import com.propertymanagement.server.dto.UserRespDto;
import com.propertymanagement.server.exception.UserNotFoundException;
import com.propertymanagement.server.repository.UserRepository;
import com.propertymanagement.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public UserRespDto save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRegisterTime(LocalDate.now());
        user = userRepository.save(user);
        return modelMapper.map(user, UserRespDto.class);
    }

    @Override
    public List<UserRespDto> findAll() {
        var users = userRepository.findAll();
        Type destinationType = new TypeToken<List<UserRespDto>>(){}.getType();
        return modelMapper.map(users,destinationType);
    }

    @Override
    public UserRespDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return modelMapper.map(userRepository.save(user), UserRespDto.class);
    }

    @Override
    public UserRespDto update(UserDto userDto, Long id) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setId(id);
        user = userRepository.save(user);
        return modelMapper.map(user, UserRespDto.class);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void activateUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        if(!user.isActive()) {
            user.setActive(true);
            userRepository.save(user);
        }
    }

    @Override
    public void deActivateUser(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        if(user.isActive()) {
            user.setActive(false);
            userRepository.save(user);
        }
    }

    @Override
    public List<UserRespDto> find10MostRecentTenants() {
        var users = userRepository.findTop10ByRoleEqualsOrderByRegisterTimeDesc(Role.TENANT);
        Type listType = new TypeToken<List<UserRespDto>>(){}.getType();
        return modelMapper.map(users,listType);
    }
}
