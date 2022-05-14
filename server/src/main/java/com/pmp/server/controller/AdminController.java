package com.pmp.server.controller;

import com.google.common.base.CaseFormat;
import com.pmp.server.domain.Role;
import com.pmp.server.domain.User;
import com.pmp.server.dto.APIResponse;
import com.pmp.server.dto.UserDTO;
import com.pmp.server.dto.common.ResponseMessage;
import com.pmp.server.security.service.AuthService;
import com.pmp.server.security.service.impl.AuthServiceImpl;
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
@CrossOrigin
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    private final AuthServiceImpl authService;

    public AdminController(UserService userService, RoleService roleService, AuthServiceImpl authService) {
        this.userService = userService;
        this.roleService = roleService;
        this.authService = authService;
    }

    @GetMapping("/landlords")
    public APIResponse<UserDTO> getLandlords(Pageable pagingRequest, @RequestParam(required = false) String keywords) {
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
    public APIResponse<UserDTO> getTenants(Pageable pagingRequest, @RequestParam(required = false) String keywords) {
        Role role = roleService.findByName(ERole.ROLE_TENANT.getRole());

        PageRequest daoPageable = PageRequest.of(
                pagingRequest.getPageNumber(),
                pagingRequest.getPageSize(),
                convertDtoSortToDaoSort(pagingRequest.getSort())
        );

        var data = userService.getAllByRoleIdAndKeywords(daoPageable, role, keywords != null ? keywords : "");
        return new APIResponse<>(data);
    }

    @PutMapping("/users/{id}/deactivate")
    public ResponseEntity<ResponseMessage> deactivateUser(@PathVariable UUID id) throws Throwable {
        var responseMessage = authService.activateUser(id, false);
        return ResponseEntity.ok(responseMessage);
    }

    @PutMapping("/users/{id}/activate")
    public ResponseEntity<ResponseMessage> activateUser(@PathVariable UUID id) throws Throwable {
        var responseMessage = authService.activateUser(id, true);

        return ResponseEntity.ok(responseMessage);
    }

    private Sort convertDtoSortToDaoSort(Sort dtoSort) {
        return Sort.by(dtoSort.get()
                .map(sortOrder -> sortOrder.withProperty(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sortOrder.getProperty())))
                .collect(Collectors.toList())
        );
    }
}
