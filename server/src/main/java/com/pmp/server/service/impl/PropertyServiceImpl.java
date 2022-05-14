package com.pmp.server.service.impl;

import com.pmp.server.domain.Property;
import com.pmp.server.domain.PropertyRentalHistory;
import com.pmp.server.domain.User;
import com.pmp.server.dto.RentDTO;
import com.pmp.server.dto.common.PagingRequest;
import com.pmp.server.repo.PropertyRentalHistoryRepo;
import com.pmp.server.repo.PropertyRepo;
import com.pmp.server.repo.UserRepo;
import com.pmp.server.service.PropertyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PropertyServiceImpl implements PropertyService {
  private final PropertyRepo propertyRepo;
  private final PropertyRentalHistoryRepo rentalRepo;

  private final UserRepo userRepo;


  public PropertyServiceImpl(PropertyRepo propertyRepo,PropertyRentalHistoryRepo rentalRepo,UserRepo userRepo) {
    this.propertyRepo = propertyRepo;
    this.rentalRepo = rentalRepo;
    this.userRepo = userRepo;
  }
  public Page<Property> findAll(Pageable pageable){
    return propertyRepo.findAll(pageable);
  }

  @Override
  public Property getById(UUID id) {
    Optional<Property> data = propertyRepo.findById(id);
    if(!data.isPresent()){
      return null;
    }
    return data.get();
  }

  @Override
  public void rent(UUID id,RentDTO rentdto) {
    PropertyRentalHistory hist = new PropertyRentalHistory();
    Optional<Property> p = propertyRepo.findById(id);
    if(p.isPresent()){
      Property pty = p.get();
      User user = userRepo.findById(UUID.fromString("b7051283-22ad-4e4f-8f74-9e71bcb32b83")).get();
      hist.setRentedBy(user);
      hist.setProperty(pty);
      hist.setEndDate(rentdto.getEndDate());
      hist.setStartDate(rentdto.getStartDate());
      rentalRepo.save(hist);

      pty.setLastRentedBy(user);
      propertyRepo.save(pty);
    }

  }

  @Override
  public Page<Property> findAllwithFilter(Pageable page,String loc, int r) {
    return propertyRepo.findAllByCityIsLikeIgnoreCaseAndAndNumberOfBedroomsGreaterThanEqual(page,loc,r);
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
