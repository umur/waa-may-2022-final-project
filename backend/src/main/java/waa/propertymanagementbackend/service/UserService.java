package waa.propertymanagementbackend.service;


import waa.propertymanagementbackend.domain.User;
import waa.propertymanagementbackend.dto.EmailDataDetailDto;
import waa.propertymanagementbackend.dto.UserDto;

import java.util.List;

public interface UserService<TDto> {
    public List<TDto> getLastRecentTenants();

    TDto getById(int id);

    public User getUserById(int id);
    public TDto getUserDtoByEmail(String email);

    public void activate(String email, boolean value);



    /*void save(TDto dto);

    List<TDto> getAll();



    void delete(int id,boolean value);
    */
 /*   LoginResponse login(LoginRequest loginRequest);



    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    void save(UsersDto dto);

    List<UsersDto> getAll();

    UsersDto getById(int id);

    void delete(int id,boolean value);

*/

}
