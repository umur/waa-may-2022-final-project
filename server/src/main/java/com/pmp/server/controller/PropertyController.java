package com.pmp.server.controller;

import com.pmp.server.domain.Property;
import com.pmp.server.dto.APIResponse;
import com.pmp.server.dto.common.PagingRequest;
import com.pmp.server.service.PropertyService;
import com.pmp.server.service.impl.PropertyServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

  private final PropertyServiceImpl propertyService;

  public PropertyController(PropertyServiceImpl propertyService) {
    this.propertyService = propertyService;
  }

  @GetMapping
  private APIResponse<Property> getProperty(Pageable pageable) {
    Page<Property> data = propertyService.findAll(pageable);
    return new APIResponse<Property>(data);
  }
//
//  @GetMapping
//  private APIResponse<List<Property>> getProducts() {
//    List<Property> allProperties = propertyService.findAllProperties();
//    return new APIResponse<>(allProperties.size(), allProperties);
//  }
//
//  @GetMapping("/{field}")
//  private APIResponse<List<Property>> getProductsWithSort(@PathVariable String field) {
//    List<Property> allProperties = propertyService.findPropertiesWithSorting(field);
//    return new APIResponse<>(allProperties.size(), allProperties);
//  }
//
//  @GetMapping("/pagination/{offset}/{pageSize}")
//  private APIResponse<Page<Property>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
//    Page<Property> productsWithPagination = propertyService.findPropertiesWithPagination(offset, pageSize);
//    return new APIResponse<>(productsWithPagination.getSize(), productsWithPagination);
//  }
//
//  @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
//  private APIResponse<Page<Property>> getProductsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {
//    Page<Property> productsWithPagination = propertyService.findPropertiesWithPaginationAndSorting(offset, pageSize, field);
//    return new APIResponse<>(productsWithPagination.getSize(), productsWithPagination);
//  }





}
