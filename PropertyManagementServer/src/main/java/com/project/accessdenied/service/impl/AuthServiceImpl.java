package com.project.accessdenied.service.impl;

import com.project.accessdenied.dto.ResponseDto;
import com.project.accessdenied.dto.UserDto;
import com.project.accessdenied.dto.UserRegisterDto;
import com.project.accessdenied.entity.Role;
import com.project.accessdenied.entity.User;
import com.project.accessdenied.model.LoginRequest;
import com.project.accessdenied.model.LoginResponse;
import com.project.accessdenied.model.RefreshTokenRequest;
import com.project.accessdenied.repository.UserRepository;
import com.project.accessdenied.security.JwtHelper;
import com.project.accessdenied.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtHelper jwtHelper;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtHelper jwtHelper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtHelper = jwtHelper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            var result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {

        }

        final String accessToken = jwtHelper.generateToken(loginRequest.getEmail());
        final String refreshToken = jwtHelper.generateRefreshToken(loginRequest.getEmail());

        var user = userRepository.findByEmail(loginRequest.getEmail());

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setRole(user.getRole().getRole());

        var loginResponse = new LoginResponse(accessToken, refreshToken, dto);
        return loginResponse;
    }

    @Override
    public ResponseDto register(UserRegisterDto dto) throws Exception {
        User u = new User();
        u.setEmail(dto.getEmail());
        u.setActive(true);
        u.setLastLoggedInAt(LocalDateTime.now());
        u.setFirstName(dto.getFirstname());
        u.setLastname(dto.getLastname());
        u.setPassword(passwordEncoder.encode(dto.getPassword()));
        Role role = new Role();
        if(dto.getRole().equalsIgnoreCase("TENANT")) {
            role.setId(3);
            role.setRole("TENANT");
        } else {
            role.setId(2);
            role.setRole("LANDLORD");
        }
        u.setRole(role);
        userRepository.save(u);

        return new ResponseDto(dto.getEmail(), dto.getRole());
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtHelper.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            final String accessToken = jwtHelper.generateToken(jwtHelper.getSubject(refreshTokenRequest.getRefreshToken()));
            var loginResponse = new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken(), null);
            return loginResponse;
        }
        return new LoginResponse();
    }

}
