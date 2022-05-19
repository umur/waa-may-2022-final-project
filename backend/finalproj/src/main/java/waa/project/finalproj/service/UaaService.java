package waa.project.finalproj.service;

import waa.project.finalproj.model.LoginRequest;
import waa.project.finalproj.model.LoginResponse;

public interface UaaService {
    LoginResponse login(LoginRequest loginRequest) throws Exception;
}
