package waa.project.finalproj.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import waa.project.finalproj.dto.user.UserDTO;
import waa.project.finalproj.dto.user.UserSaveDTO;
import waa.project.finalproj.entity.User;
import waa.project.finalproj.repository.UserRepository;
import waa.project.finalproj.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public void add(UserSaveDTO t) {
        t.setPassword(new BCryptPasswordEncoder().encode(t.getPassword()));
        userRepository.save(modelMapper.map(t, User.class));
    }

    @Override
    public void delete(int id) {
        var j = userRepository.findById(id);
        if (j.isPresent()){
            j.get().setDeletedAt(LocalDate.now());
            userRepository.save(j.get());
        }
    }

    @Override
    public void update(int id, UserDTO t) {
        var l = userRepository.findById(id);
        if (l.isPresent()){
            l.get().setActive(t.isActive());
            l.get().setFirstname(t.getFirstname());
            l.get().setLastname(t.getLastname());
            l.get().setEmail(t.getEmail());
            l.get().setRole(t.getRole());
            userRepository.save(l.get());
        }
    }

    @Override
    public List<UserDTO> findAll() {
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .map(u -> modelMapper.map(u, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(int id) {
        var h = userRepository.findById(id);

        return h.isPresent() && h.get().getDeletedAt() == null ? modelMapper.map(h.get(), UserDTO.class) : null;

    }

    @Override
    public List<UserDTO> findAllWhereDeletedAtNotNull() {
        return StreamSupport
                .stream(userRepository.findAllByDeletedAtIsNull().spliterator(), false)
                .map(u -> modelMapper.map(u, UserDTO.class))
                .collect(Collectors.toList());
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

        if (token.equals(userOptional.get().getResetPasswordToken())){
            userOptional.get().setPassword(new BCryptPasswordEncoder().encode(password));
            userOptional.get().setResetPasswordToken(null);
            userRepository.save(userOptional.get());
            return userOptional.get();
        }
        return null;
    }

    @Override
    public List<UserDTO> findAllByRoleAndDeletedAtIsNullOrderByIdDesc(String role) {
        return StreamSupport
                .stream(userRepository.findTop10ByRoleAndDeletedAtIsNullOrderByIdDesc(role).spliterator(), false)
                .map(u -> modelMapper.map(u, UserDTO.class)).limit(10)
                .collect(Collectors.toList());
    }
}
