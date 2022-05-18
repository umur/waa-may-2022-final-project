package com.pmp.server.controller;

import com.pmp.server.domain.PropertyRentalHistory;
import com.pmp.server.domain.User;
import com.pmp.server.dto.common.PagingRequest;
import com.pmp.server.dto.common.ResponseMessage;
import com.pmp.server.service.impl.UserServiceImpl;
import org.apache.catalina.connector.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
@CrossOrigin
public class UserController {
  private final UserServiceImpl userService;

  public UserController(UserServiceImpl userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseMessage saveUser(@RequestBody @Valid User user){
    return userService.saveUser(user);

  }

  @GetMapping("{id}")
  public ResponseMessage getUser(@PathVariable UUID id) {
    return userService.getUserById(id);
  }

  @GetMapping()
  public ResponseMessage getAllUser() {
    return userService.getAllUser();
  }

  @PostMapping("/paginated")
  public Page<User> getAllPaginatedUser(@RequestBody PagingRequest pagingRequest) {
    return userService.getAllUserPaginated(pagingRequest);
  }

  @GetMapping("/rental-history")
  private ResponseMessage getRental(){
    List<PropertyRentalHistory> list = userService.getRental();
    return new ResponseMessage("success", HttpStatus.OK,list);
  }
}

// pprajapati: spring-boot-bean-validation: https://examples.javacodegeeks.com/spring-boot-bean-validation-example/
