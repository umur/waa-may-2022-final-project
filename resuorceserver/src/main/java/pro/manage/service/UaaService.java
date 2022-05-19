package pro.manage.service;

import pro.manage.model.LoginResponse;
import pro.manage.model.LoginRequest;


public interface UaaService {
    LoginResponse login(LoginRequest loginRequest) throws Exception;
}
