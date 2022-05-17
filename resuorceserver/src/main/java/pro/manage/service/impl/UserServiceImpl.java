package pro.manage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.manage.entity.User;
import pro.manage.entity.dto.UserDto;
import pro.manage.repository.UserRepository;
import pro.manage.service.UserService;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final  UserRepository userRepository;

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


}
