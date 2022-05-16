package com.property.service;

import com.property.dto.request.EmailRequest;
import com.property.domain.Role;
import com.property.dto.request.LoginRequest;
import com.property.dto.request.PasswordRequest;
import com.property.dto.response.LoginResponse;
import com.property.dto.request.UserRegistrationRequest;
import com.property.dto.response.UserRegistrationResponse;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends CrudService<UserRegistrationRequest, UserRegistrationResponse, Long>{

    LoginResponse login(LoginRequest loginRequest);


    void userIsActive(long id);

    void processForgotPassword(EmailRequest request);

    UserRegistrationResponse resetPassword(PasswordRequest passwordRequest, HttpServletRequest request);

}
