package com.project.accessdenied.service;

import com.project.accessdenied.dto.ResponseDto;
import com.project.accessdenied.dto.UserLoginDto;
import com.project.accessdenied.dto.UserRegisterDto;
import com.project.accessdenied.model.LoginRequest;
import com.project.accessdenied.model.LoginResponse;
import com.project.accessdenied.model.RefreshTokenRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    ResponseDto register(UserRegisterDto dto) throws Exception;
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
