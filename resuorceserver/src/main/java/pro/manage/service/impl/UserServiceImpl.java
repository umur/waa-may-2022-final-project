package pro.manage.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import pro.manage.entity.User;
import pro.manage.entity.dto.UserDto;

import pro.manage.entity.dto.UserSaveDTO;
import pro.manage.repository.UserRepository;

import pro.manage.service.UserDTO;
import pro.manage.service.UserService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserRepository getRepo() {
        return userRepository;
    }

    @Override
    public Class<UserDto> getDTOType() {
        return UserDto.class;
    }

    @Override
    public Class<User> getTType() {
        return User.class;
    }

    @Override
    public String forgotPassword(String email) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            throw new Exception();
        }
        User user = userOptional.get();
        user.setResetPasswordToken(Utility.generateToken());
        user = userRepository.save(user);
        return user.getResetPasswordToken();
    }

    @Override
    public User resetPassword(String token, String password) throws Exception {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByResetPasswordToken(token));

        if (!userOptional.isPresent()) {
            throw new Exception();
        }

        if (token.equals(userOptional.get().getResetPasswordToken())) {
            userOptional.get().setPassword(new BCryptPasswordEncoder().encode(password));
            userOptional.get().setResetPasswordToken(null);
            userRepository.save(userOptional.get());
            return userOptional.get();
        }
        return null;
    }

    @Override
    public List<UserDTO> findAllWhereDeletedAtNotNull() {
        return null;
    }

    @Override
    public List<UserDTO> findAllByRoleAndDeletedAtIsNullOrderByIdDesc(String role) {
        return StreamSupport
                .stream(userRepository.findTop10ByRoleAndDeletedAtIsNullOrderByIdDesc(role).spliterator(), false)
                .map(u -> modelMapper.map(u, UserDTO.class)).limit(10)
                .collect(Collectors.toList());
    }
    public void add(UserSaveDTO t) {
        t.setPassword(new BCryptPasswordEncoder().encode(t.getPassword()));
        userRepository.save(modelMapper.map(t, User.class));
    }
}
