package com.pmp.server.controller;

import com.pmp.server.domain.Role;
import com.pmp.server.domain.User;
import com.pmp.server.dto.APIResponse;
import com.pmp.server.service.RoleService;
import com.pmp.server.service.UserService;
import com.pmp.server.utils.enums.ERole;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tenants")
public class TenantController {
    private final UserService userService;
    private final RoleService roleService;

    public TenantController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public APIResponse<User> getTenants(Pageable pageable) {
        Role role = roleService.findByName(ERole.ROLE_TENANT.getRole());
        var data = userService.getAllUserByRole(pageable, role);
        return new APIResponse<>(data);
    }
}
