package com.pmp.server.controller;

import com.google.common.base.CaseFormat;
import com.pmp.server.domain.Role;
import com.pmp.server.domain.User;
import com.pmp.server.dto.APIResponse;
import com.pmp.server.exception.UserNotFoundException;
import com.pmp.server.service.RoleService;
import com.pmp.server.service.UserService;
import com.pmp.server.utils.enums.ERole;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/landlords")
    public APIResponse<User> getLandlords(Pageable pagingRequest, @RequestParam(required = false) String keywords) {
        Role role = roleService.findByName(ERole.ROLE_LANDLORD.getRole());
        PageRequest daoPageable = PageRequest.of(
                pagingRequest.getPageNumber(),
                pagingRequest.getPageSize(),
                convertDtoSortToDaoSort(pagingRequest.getSort())
        );

        var data = userService.getAllByRoleIdAndKeywords(daoPageable, role, keywords != null ? keywords : "");
        return new APIResponse<>(data);
    }

    @GetMapping("/tenants")
    public APIResponse<User> getTenants(Pageable pagingRequest, @RequestParam(required = false) String keywords) {
        Role role = roleService.findByName(ERole.ROLE_TENANT.getRole());

        PageRequest daoPageable = PageRequest.of(
                pagingRequest.getPageNumber(),
                pagingRequest.getPageSize(),
                convertDtoSortToDaoSort(pagingRequest.getSort())
        );

        var data = userService.getAllByRoleIdAndKeywords(daoPageable, role, keywords);
        return new APIResponse<>(data);
    }

    @PutMapping("/users/{id}/deactivate")
    public ResponseEntity<User> deactivateUser(@PathVariable UUID id) throws Throwable {
        var user = userService.updateUserStatus(id, false);

        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}/activate")
    public ResponseEntity<User> activateUser(@PathVariable UUID id) throws Throwable {
        var user = userService.updateUserStatus(id, true);

        return ResponseEntity.ok(user);
    }

    private Sort convertDtoSortToDaoSort(Sort dtoSort) {
        return Sort.by(dtoSort.get()
                .map(sortOrder -> sortOrder.withProperty(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sortOrder.getProperty())))
                .collect(Collectors.toList())
        );
    }
}
