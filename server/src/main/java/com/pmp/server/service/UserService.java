package com.pmp.server.service;

import com.pmp.server.domain.PropertyRentalHistory;
import com.pmp.server.domain.Role;
import com.pmp.server.domain.User;
import com.pmp.server.dto.common.PagingRequest;
import com.pmp.server.dto.common.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserService {
  ResponseMessage saveUser(User u);
  ResponseMessage getUserById(UUID id);
  ResponseMessage getAllUser();

  Page<User> getAllUserPaginated(PagingRequest pagingRequest);

  Page<User> getAllUserByRole(Pageable pageable, Role role);

  Page<User> getAllByRoleIdAndKeywords(Pageable pageable, Role role, String keywords);

  List<PropertyRentalHistory> getRental();

  List<PropertyRentalHistory> getRentalOfOwner();
}
