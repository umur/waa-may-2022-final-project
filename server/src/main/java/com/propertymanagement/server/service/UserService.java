package com.propertymanagement.server.service;

import com.propertymanagement.server.dto.UserDto;
import com.propertymanagement.server.dto.UserRespDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService extends CrudService<UserDto, UserRespDto, Long>{

    void activateUser(long id);

    void deActivateUser(long id);

    List<UserRespDto> find10MostRecentTenants();

}
