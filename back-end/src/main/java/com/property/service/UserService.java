package com.property.service;

import com.property.dto.LoginRequest;
import com.property.dto.LoginResponse;
import com.property.dto.UserRegistration;

public interface UserService extends CrudService<UserRegistration, Long>{

    LoginResponse login(LoginRequest loginRequest);
}
