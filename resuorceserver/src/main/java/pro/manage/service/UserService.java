package pro.manage.service;

import pro.manage.entity.User;

import pro.manage.entity.dto.UserSaveDTO;
import pro.manage.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserService extends GenericService<User, pro.manage.entity.dto.UserDto, UUID, UserRepository> {
    List<UserDTO> findAllWhereDeletedAtNotNull();
    List<UserDTO> findAllByRoleAndDeletedAtIsNullOrderByIdDesc(String role);
    String forgotPassword(String email) throws Exception;
    User resetPassword(String token, String password) throws Exception;
    void add(UserSaveDTO t);
}
