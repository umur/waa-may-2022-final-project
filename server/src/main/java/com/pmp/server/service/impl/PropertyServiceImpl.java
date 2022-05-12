package com.pmp.server.service.impl;

import com.pmp.server.domain.Property;
import com.pmp.server.repo.PropertyRepo;
import com.pmp.server.service.PropertyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
  private final PropertyRepo propertyRepo;

  public PropertyServiceImpl(PropertyRepo propertyRepo) {
    this.propertyRepo = propertyRepo;
  }

//  public List<Property>
//  getAllProperties(Integer pageNo, Integer pageSize, String sortBy){
//    return null;
//    Pageable paging = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//
//    Page<Property> pagedResult = (Page<Property>) propertyRepo.findAll(paging);
//
//    if(pagedResult.hasContent()) {
//      return pagedResult.getContent();
//    } else {
//      return new ArrayList<Property>();
//    }
//  }

//  public List<Property> findAllProperties() {
//    return (List<Property>) propertyRepo.findAll();
//  }
//
//
//  public List<Property> findPropertiesWithSorting(String field){
//    return (List<Property>) propertyRepo.findAll(Sort.by(Sort.Direction.ASC,field));
//  }
//
//
//  public Page<Property> findPropertiesWithPagination(int offset,int pageSize){
//    Page<Property> products = propertyRepo.findAll(PageRequest.of(offset, pageSize));
//    return  products;
//  }
//
//  public Page<Property> findPropertiesWithPaginationAndSorting(int offset,int pageSize,String field){
//    Page<Property> products = propertyRepo.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
//    return  products;
//  }

}
