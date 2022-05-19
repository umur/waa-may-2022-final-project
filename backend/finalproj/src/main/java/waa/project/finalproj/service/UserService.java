package waa.project.finalproj.service;

import waa.project.finalproj.dto.user.UserDTO;
import waa.project.finalproj.dto.user.UserSaveDTO;
import waa.project.finalproj.entity.User;

import java.util.List;

public interface UserService {
    void add(UserSaveDTO t);
    void delete(int id);
    void update(int id, UserDTO t);
    List<UserDTO> findAll();
    UserDTO findById(int id);
    List<UserDTO> findAllWhereDeletedAtNotNull();
    List<UserDTO> findAllByRoleAndDeletedAtIsNullOrderByIdDesc(String role);

    ////


    ///

    String forgotPassword(String email) throws Exception;
    User resetPassword(String token, String password) throws Exception;
}
