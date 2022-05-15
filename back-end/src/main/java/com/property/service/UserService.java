package com.property.service;

import com.property.domain.Role;
import com.property.dto.request.LoginRequest;
import com.property.dto.response.LoginResponse;
import com.property.dto.request.UserRegistrationRequest;
import com.property.dto.response.UserRegistrationResponse;

public interface UserService extends CrudService<UserRegistrationRequest, UserRegistrationResponse, Long>{

    LoginResponse login(LoginRequest loginRequest);

//    void userActive(long id, Role role);
}
