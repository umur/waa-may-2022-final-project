package com.property.service.impls;

import com.property.domain.Role;
import com.property.domain.User;
import com.property.dto.request.LoginRequest;
import com.property.dto.response.LoginResponse;
import com.property.dto.request.UserRegistrationRequest;
import com.property.dto.response.UserRegistrationResponse;
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
    public UserRegistrationResponse save(UserRegistrationRequest userRegistration) {
        User user = modelMapper.map(userRegistration, User.class);
        user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        user = userRepository.save(user);
        return modelMapper.map(user, UserRegistrationResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRegistrationResponse> findAll() {
        var users = userRepository.findAll();
        Type listType = new TypeToken<List<UserRegistrationResponse>>(){}.getType();
        return modelMapper.map(users,listType);
    }

    @Override
    public UserRegistrationResponse findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("User does not exist %s",id)));
        return modelMapper.map(userRepository.save(user), UserRegistrationResponse.class);
    }

    @Override
    public UserRegistrationResponse update(UserRegistrationRequest userRegistration, Long id) {
        User user = modelMapper.map(userRegistration, User.class);
        user.setId(id);
        user = userRepository.save(user);
        return modelMapper.map(user, UserRegistrationResponse.class);
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

    @Override
    public void userIsActive(long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("User does not exist %s",id)));
        var status = user.isActive() ? false:true;
        user.setActive(status);
        userRepository.save(user);
    }
}
