package com.pmp.server.controller;

import com.google.common.base.CaseFormat;
import com.pmp.server.domain.Role;
import com.pmp.server.domain.User;
import com.pmp.server.dto.APIResponse;
import com.pmp.server.service.RoleService;
import com.pmp.server.service.UserService;
import com.pmp.server.utils.enums.ERole;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

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

    private Sort convertDtoSortToDaoSort(Sort dtoSort) {
        return Sort.by(dtoSort.get()
                .map(sortOrder -> sortOrder.withProperty(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sortOrder.getProperty())))
                .collect(Collectors.toList())
        );
    }
}
