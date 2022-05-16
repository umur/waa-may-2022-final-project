package pro.manage.controller;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.manage.entity.Role;
import pro.manage.service.RoleService;


@RestController
@CrossOrigin(allowedHeaders = "*")
@RequestMapping(value = "/user/role")
@AllArgsConstructor
public class RoleController implements GenericController<Role,Long, RoleService>{


    private final RoleService roleService;

    @Override
    public RoleService getService() {
        return roleService;
    }
}
