package com.pmp.server.controller;

import com.google.common.base.CaseFormat;
import com.pmp.server.domain.PropertyRentalHistory;
import com.pmp.server.domain.Role;
import com.pmp.server.domain.User;
import com.pmp.server.dto.common.PagingResponse;
import com.pmp.server.dto.common.ResponseMessage;
import com.pmp.server.security.service.impl.AuthServiceImpl;
import com.pmp.server.service.PropertyRentalHistoryService;
import com.pmp.server.service.RoleService;
import com.pmp.server.service.UserService;
import com.pmp.server.service.impl.PropertyRentalServiceImpl;
import com.pmp.server.service.impl.PropertyServiceImpl;
import com.pmp.server.utils.enums.ERole;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/admin")
@CrossOrigin
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    private final AuthServiceImpl authService;

    private final PropertyServiceImpl propertyService;

    private final PropertyRentalServiceImpl rentalHistory;

    public AdminController(UserService userService, RoleService roleService, AuthServiceImpl authService, PropertyServiceImpl propertyService,PropertyRentalServiceImpl rentalHistory) {
        this.userService = userService;
        this.roleService = roleService;
        this.authService = authService;
        this.propertyService = propertyService;
        this.rentalHistory = rentalHistory;
    }

    @GetMapping("/landlords")
    public PagingResponse<User> getLandlords(Pageable pagingRequest, @RequestParam(required = false) String keywords) {
        Role role = roleService.findByName(ERole.ROLE_LANDLORD.getRole());
        PageRequest daoPageable = PageRequest.of(
                pagingRequest.getPageNumber(),
                pagingRequest.getPageSize(),
                convertDtoSortToDaoSort(pagingRequest.getSort())
        );

        var data = userService.getAllByRoleIdAndKeywords(daoPageable, role, keywords != null ? keywords : "");
        return new PagingResponse<>(data);
    }

    @GetMapping("/tenants")
    public PagingResponse<User> getTenants(Pageable pagingRequest, @RequestParam(required = false) String keywords) {
        Role role = roleService.findByName(ERole.ROLE_TENANT.getRole());

        PageRequest daoPageable = PageRequest.of(
                pagingRequest.getPageNumber(),
                pagingRequest.getPageSize(),
                convertDtoSortToDaoSort(pagingRequest.getSort())
        );
        var data = userService.getAllByRoleIdAndKeywords(daoPageable, role, keywords);
        return new PagingResponse<>(data);

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

//    @GetMapping("/propertyincome")
//    public ResponseEntity<ResponseMessage> perpertyIncome(){
//        return ResponseEntity.ok( propertyService.propertyIncome());
//    }

    private Sort convertDtoSortToDaoSort(Sort dtoSort) {
        return Sort.by(dtoSort.get()
                .map(sortOrder -> sortOrder.withProperty(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sortOrder.getProperty())))
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/properties/rental-history")
    public ResponseMessage count(){
        Object list = rentalHistory.findAll();
        return new ResponseMessage("success", HttpStatus.OK,list);
    }

}
