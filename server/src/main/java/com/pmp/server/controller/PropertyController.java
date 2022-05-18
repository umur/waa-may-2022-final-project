package com.pmp.server.controller;

import com.google.common.base.CaseFormat;
import com.pmp.server.domain.Property;
import com.pmp.server.domain.PropertyRentalHistory;
import com.pmp.server.domain.Role;
import com.pmp.server.domain.User;
import com.pmp.server.dto.NotificationDTO;
import com.pmp.server.dto.PropertyIncomeDTO;
import com.pmp.server.dto.PropertyDTO;
import com.pmp.server.dto.RentDTO;
import com.pmp.server.dto.common.PagingResponse;
import com.pmp.server.dto.common.ResponseMessage;
import com.pmp.server.service.PropertyService;
import com.pmp.server.service.impl.PropertyServiceImpl;
import com.pmp.server.service.impl.UserServiceImpl;
import com.pmp.server.utils.enums.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin
public class PropertyController {
  private final SimpMessagingTemplate template;
  private final PropertyServiceImpl propertyService;
  private final UserServiceImpl userService;

  public PropertyController(PropertyServiceImpl propertyService, UserServiceImpl userService,SimpMessagingTemplate template) {
    this.propertyService = propertyService;
    this.userService = userService;
    this.template = template;
  }

  @GetMapping
  private PagingResponse<Property> getProperty(Pageable pageable, @RequestParam Optional<String> location, @RequestParam Optional<Integer> room) {
    if(location.isPresent()||room.isPresent()){
      var loc = location.isPresent()?"%"+location.get()+"%":"%%";
      var r = room.isPresent()?room.get():0;
      Page<Property> data = propertyService.findAllwithFilter(pageable,loc,r);
      return new PagingResponse<Property>(data);
    }else{
      Page<Property> data = propertyService.findAll(pageable);
      return new PagingResponse<Property>(data);
    }

  }
  @GetMapping("/{id}")
  private ResponseEntity<Property> getByID(@PathVariable UUID id){
    Property data = propertyService.getById(id);
    return ResponseEntity.ok(data);
  }

  @PostMapping("/rent/{id}")
  public ResponseMessage rent(@PathVariable UUID id, @RequestBody RentDTO body){
    PropertyRentalHistory hist = propertyService.rent(id,body);

    return new ResponseMessage("success", HttpStatus.CREATED,hist);
  }

  @GetMapping("/property-by-income")
  private ResponseMessage getPropertyByIncome(@RequestParam Optional<UUID> userId){
    UUID uId = null;
    if(userId.isPresent()){
      uId = userId.get();
    }
    return propertyService.propertyByIncome(uId);
  }

  @GetMapping("/paginated")
  public PagingResponse<Property> getAllPaginatedProperties(Pageable pagingRequest) {
    var data = propertyService.getAllPaginatedProperties(pagingRequest);
    return new PagingResponse<>(data);

  }

  @GetMapping("/paginated/{ownerId}")
  public PagingResponse<Property> getAllLandlordProperties(Pageable pagingRequest, @PathVariable UUID ownerId) {
    var data = propertyService.getAllLandlordProperties(pagingRequest, ownerId);
    return new PagingResponse<>(data);

  }

  @GetMapping("/rented/paginated")
  public PagingResponse<Property> getAllRentedProperties(Pageable pagingRequest) {
    var data = propertyService.getAllRentedProperties(pagingRequest);
    return new PagingResponse<>(data);

  }


  private Sort convertDtoSortToDaoSort(Sort dtoSort) {
    return Sort.by(dtoSort.get()
      .map(sortOrder -> sortOrder.withProperty(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sortOrder.getProperty())))
      .collect(Collectors.toList())
    );
  }

  @GetMapping("/test/noti")
  public ResponseEntity<String> send() throws Exception {
    this.template.convertAndSend("/topic/tenants", new NotificationDTO("sai","testmsg"));
    this.template.convertAndSend("/topic/landlords", new NotificationDTO("ROLE_LANDLORD","test msg"));
    return ResponseEntity.ok("Success");
  }


}
