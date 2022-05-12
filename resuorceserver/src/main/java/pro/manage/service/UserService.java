package pro.manage.service;

import pro.manage.entity.User;
import pro.manage.entity.dto.UserDto;
import pro.manage.repository.UserRepository;

import java.util.List;
import java.util.Optional;


public interface UserService extends GenericService<User, UserDto, Long, UserRepository> {

}
