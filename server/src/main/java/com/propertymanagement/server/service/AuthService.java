package com.propertymanagement.server.service;

import com.propertymanagement.server.domain.PasswordDto;
import com.propertymanagement.server.dto.LoginDto;
import com.propertymanagement.server.dto.LoginRespDto;
import com.propertymanagement.server.dto.UserRespDto;


public interface AuthService {
    LoginRespDto login(LoginDto loginDto);

    LoginRespDto logins(LoginDto loginDto);

    void sendForgotPasswordEmail(String email);

    UserRespDto resetPassword(PasswordDto passwordDto, String token);
}
