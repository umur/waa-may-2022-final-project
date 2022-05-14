package com.pmp.server.controller;

import com.pmp.server.domain.Property;
import com.pmp.server.dto.RentDTO;
import com.pmp.server.dto.common.PagingResponse;
import com.pmp.server.dto.common.ResponseMessage;
import com.pmp.server.service.impl.PropertyServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin
public class PropertyController {

  private final PropertyServiceImpl propertyService;

  public PropertyController(PropertyServiceImpl propertyService) {
    this.propertyService = propertyService;
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
  private ResponseMessage getByID(@PathVariable UUID id, @RequestBody RentDTO body){
    propertyService.rent(id,body);
    return new ResponseMessage("success", HttpStatus.CREATED);
  }



}
