package pro.manage.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.manage.entity.dto.UserDto;
import pro.manage.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController implements GenericController<UserDto, UUID, UserService> {


    private final UserService userService;

    @Override
    public UserService getService() {
        return userService;
    }

}


