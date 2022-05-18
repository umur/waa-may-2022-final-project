package com.pmp.server.service.impl;

import com.pmp.server.domain.PropertyRentalHistory;
import com.pmp.server.domain.Role;
import com.pmp.server.domain.User;
import com.pmp.server.dto.common.PagingRequest;
import com.pmp.server.dto.common.ResponseMessage;
import com.pmp.server.repo.PropertyRentalHistoryRepo;
import com.pmp.server.repo.UserRepo;
import com.pmp.server.service.UserService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.pmp.server.utils.constants.ResponseMessageConstants.*;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepo userRepo;
  private final PropertyRentalHistoryRepo rentalRepo;

  public UserServiceImpl(UserRepo userRepo,PropertyRentalHistoryRepo rentalRepo) {
    this.userRepo = userRepo;
    this.rentalRepo = rentalRepo;
  }


  @Override
  public ResponseMessage saveUser(User u) {
    User user = userRepo.save(u);
    return new ResponseMessage(SUCCESSFULLY_CREATED, HttpStatus.CREATED, user);
  }

  @Override
  public ResponseMessage getUserById(UUID id) {
    Optional<User> user = userRepo.findById(id);
    return new ResponseMessage(SUCCESSFUL_MESSAGE, HttpStatus.OK, user);

  }

  @Override
  public ResponseMessage getAllUser() {
    List<User> users = (List<User>) userRepo.findAll();
    return new ResponseMessage(SUCCESSFUL_MESSAGE, HttpStatus.OK, users);
  }

  @Override
  public Page<User> getAllUserPaginated(PagingRequest pagingRequest) {
    var direction = (pagingRequest.isAscending()) ? Sort.Direction.ASC : Sort.Direction.DESC;

    var request = PageRequest
      .of(pagingRequest.getPage(), pagingRequest.getPageSize(), direction,pagingRequest.getSortBy());

    return userRepo.findAll(request);
  }

  @Override
  public Page<User> getAllUserByRole(Pageable pageable, Role role) {
    return userRepo.findAllByRoleId(pageable, role.getId());
  }

  @Override
  public Page<User> getAllByRoleIdAndKeywords(Pageable pageable, Role role, String keywords) {
    return userRepo.findAllWithJPQL(role.getId(), keywords, pageable);
  }

  @Override
  public List<PropertyRentalHistory> getRental() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UUID uuid = null;
    if (authentication != null) {
      if (authentication.getPrincipal() instanceof KeycloakPrincipal) {
        KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
        uuid = UUID.fromString(kp.getKeycloakSecurityContext().getToken().getSubject());
      }
    }
    User user = userRepo.findById(uuid).get();
    return rentalRepo.findAllByRentedBy(user);
  }

  @Override
  public List<PropertyRentalHistory> getRentalOfOwner() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UUID uuid = null;
    if (authentication != null) {
      if (authentication.getPrincipal() instanceof KeycloakPrincipal) {
        KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
        uuid = UUID.fromString(kp.getKeycloakSecurityContext().getToken().getSubject());
      }
    }
    User user = userRepo.findById(uuid).get();
    return rentalRepo.findAllByPropertyOwnedBy(user);
  }
}
