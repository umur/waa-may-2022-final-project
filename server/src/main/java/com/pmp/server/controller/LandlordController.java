package com.pmp.server.controller;

import com.pmp.server.domain.Role;
import com.pmp.server.domain.User;
import com.pmp.server.dto.APIResponse;
import com.pmp.server.dto.common.PagingRequest;
import com.pmp.server.service.RoleService;
import com.pmp.server.service.UserService;
import com.pmp.server.utils.enums.ERole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/landlords")
public class LandlordController {

    private final UserService userService;
    private final RoleService roleService;

    public LandlordController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public APIResponse<User> getLandlords(Pageable pagingRequest) {
        Role role = roleService.findByName(ERole.ROLE_LANDLORD.getRole());
        var data = userService.getAllUserByRole(pagingRequest, role);

        return new APIResponse<User>(data);
    }
}
