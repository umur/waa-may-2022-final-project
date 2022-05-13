package com.property.service.impls;

import com.property.domain.User;
import com.property.dto.LoginRequest;
import com.property.dto.LoginResponse;
import com.property.dto.UserRegistration;
import com.property.exception.custom.UserNotFoundException;
import com.property.respository.UserRepository;
import com.property.security.JwtHelper;
import com.property.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtHelper jwtHelper;

    @Override
    public UserRegistration save(UserRegistration userRegistration) {
        User user = modelMapper.map(userRegistration, User.class);
        user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        user = userRepository.save(user);
        return modelMapper.map(user, UserRegistration.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRegistration> findAll() {
        var users = userRepository.findAll();
        Type listType = new TypeToken<List<UserRegistration>>(){}.getType();
        return modelMapper.map(users,listType);
    }

    @Override
    public UserRegistration findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("User does not exist %s",id)));
        return modelMapper.map(userRepository.save(user),UserRegistration.class);
    }

    @Override
    public UserRegistration update(UserRegistration userRegistration, Long id) {
        User user = modelMapper.map(userRegistration, User.class);
        user.setId(id);
        user = userRepository.save(user);
        return modelMapper.map(user, UserRegistration.class);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new UserNotFoundException("Bad Credentials");
        }
        final String accessToken = jwtHelper.generateToken(loginRequest.getEmail());
        final String refreshToken = jwtHelper.generateRefreshToken(loginRequest.getEmail());
        var loginResponse = new LoginResponse(accessToken, refreshToken);
        return loginResponse;
    }
}
