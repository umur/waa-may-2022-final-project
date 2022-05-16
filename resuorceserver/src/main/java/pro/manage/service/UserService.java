package pro.manage.service;

import pro.manage.entity.User;
import pro.manage.entity.dto.UserDto;
import pro.manage.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserService extends GenericService<User, UserDto, UUID, UserRepository> {

}
