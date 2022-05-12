package pro.manage.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pro.manage.entity.Role;
import pro.manage.service.RoleService;


@RestController
@CrossOrigin(allowedHeaders = "*")
@RequestMapping(value = "/user/role")
@RequiredArgsConstructor
public class RoleController implements GenericController<Role,Long, RoleService>{
    RoleService roleService;

    @Override
    public RoleService getService() {
        return roleService;
    }
}
