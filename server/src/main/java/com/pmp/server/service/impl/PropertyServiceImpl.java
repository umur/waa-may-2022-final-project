package com.pmp.server.service.impl;

import com.pmp.server.domain.Property;
import com.pmp.server.domain.PropertyImage;
import com.pmp.server.domain.PropertyRentalHistory;
import com.pmp.server.domain.User;
import com.pmp.server.dto.PropertyDTO;
import com.pmp.server.dto.RentDTO;
import com.pmp.server.dto.common.PagingRequest;
import com.pmp.server.exceptionHandler.exceptions.CustomErrorException;
import com.pmp.server.repo.PropertyImageRepo;
import com.pmp.server.repo.PropertyRentalHistoryRepo;
import com.pmp.server.repo.PropertyRepo;
import com.pmp.server.repo.UserRepo;
import com.pmp.server.service.PropertyService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

  private final PropertyImageRepo imageRepo;


  public PropertyServiceImpl(PropertyRepo propertyRepo,PropertyRentalHistoryRepo rentalRepo,UserRepo userRepo,PropertyImageRepo imageRepo) {
    this.propertyRepo = propertyRepo;
    this.rentalRepo = rentalRepo;
    this.userRepo = userRepo;
    this.imageRepo= imageRepo;
  }
  public Page<Property> findAll(Pageable pageable){
    return propertyRepo.findAll(pageable);
  }

  @Override
  public Property getById(UUID id) {
    Optional<Property> data = propertyRepo.findById(id);
    if(!data.isPresent()){
      throw new CustomErrorException(HttpStatus.NOT_FOUND,"Property Not found");
    }
    return data.get();
  }

  @Override
  public void rent(UUID id,RentDTO rentdto) {
    PropertyRentalHistory hist = new PropertyRentalHistory();
    Optional<Property> p = propertyRepo.findById(id);
    if(p.isPresent()){
      Property pty = p.get();
      String uuid = null;
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication != null) {
        if (authentication.getPrincipal() instanceof KeycloakPrincipal) {
          KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
           uuid = kp.getKeycloakSecurityContext().getToken().getId();
        }
      }

      User user = userRepo.findById(UUID.fromString(uuid)).get();
      hist.setRentedBy(user);
      hist.setProperty(pty);
      hist.setEndDate(rentdto.getEndDate());
      hist.setStartDate(rentdto.getStartDate());
      rentalRepo.save(hist);

      pty.setLastRentedBy(user);
      propertyRepo.save(pty);
    }else{
      throw new CustomErrorException(HttpStatus.NOT_FOUND,"Property not found!");
    }

  }

  @Override
  public Page<Property> findAllwithFilter(Pageable page,String loc, int r) {
    return propertyRepo.findAllByCityIsLikeIgnoreCaseAndAndNumberOfBedroomsGreaterThanEqual(page,loc,r);
  }

  @Override
  public Page<Property> findAllByOwner(Pageable page) {
    UUID uuid = UUID.fromString("655cb8f5-80c9-43af-830c-f8b309d9e508");
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    if (authentication != null) {
//      if (authentication.getPrincipal() instanceof KeycloakPrincipal) {
//        KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
//        uuid = UUID.fromString(kp.getKeycloakSecurityContext().getToken().getId());
//      }
//    }
    User user = userRepo.findById(uuid).get();
    return propertyRepo.findAllByOwnedBy(page,user);
  }

  @Override
  public void save(PropertyDTO pty) {
    UUID owner = UUID.fromString("655cb8f5-80c9-43af-830c-f8b309d9e508");
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    if (authentication != null) {
//      if (authentication.getPrincipal() instanceof KeycloakPrincipal) {
//        KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
//        uuid = UUID.fromString(kp.getKeycloakSecurityContext().getToken().getId());
//      }
//    }
    User user = userRepo.findById(owner).get();
    List<PropertyImage> image = pty.getPhotos();
    List<PropertyImage> imgs = image.stream().map(item->{
      var img = new PropertyImage();
      img.setImageUrl(item.getImageUrl());
      imageRepo.save(img);
      return img;
    }).collect(Collectors.toList());
    Property p = new Property();
    p.setLastRentedBy(null);
    p.setCity(pty.getCity());
    p.setDescription(pty.getDescription());
    p.setPropertyName(pty.getPropertyName());
    p.setPropertyType(pty.getPropertyType());
    p.setPhotos(imgs);
    p.setOccupied(false);
    p.setNumberOfBathrooms(p.getNumberOfBathrooms());
    p.setNumberOfBedrooms(p.getNumberOfBedrooms());
    p.setState(p.getState());
    p.setZipCode(p.getZipCode());
    p.setStreetAddress(p.getStreetAddress());
    p.setRentAmount(p.getRentAmount());
    p.setOwnedBy(user);
    p.setSecurityDepositAmount(p.getSecurityDepositAmount());
    propertyRepo.save(p);
  }

  @Override
  public Page<Property> search(Pageable page,String s) {
    return propertyRepo.customSearch(page,"%"+s.toLowerCase()+"%");
  }

  @Override
  public void delete(UUID s) {
    Optional<Property> p = propertyRepo.findById(s);
    if(p.isPresent()){
      propertyRepo.delete(p.get());
    }else{
      throw new CustomErrorException(HttpStatus.NOT_FOUND,"Property not found");
    }

  }

  @Override
  public void update(PropertyDTO pty, UUID s) {
    UUID owner = UUID.fromString("655cb8f5-80c9-43af-830c-f8b309d9e508");
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    if (authentication != null) {
//      if (authentication.getPrincipal() instanceof KeycloakPrincipal) {
//        KeycloakPrincipal<KeycloakSecurityContext> kp = (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
//        uuid = UUID.fromString(kp.getKeycloakSecurityContext().getToken().getId());
//      }
//    }
    User user = userRepo.findById(owner).get();
    List<PropertyImage> image = pty.getPhotos();
    List<PropertyImage> imgs = image.stream().map(item->{
      var img = new PropertyImage();
      img.setImageUrl(item.getImageUrl());
      imageRepo.save(img);
      return img;
    }).collect(Collectors.toList());

    if(propertyRepo.findById(s).isPresent()){
      Property p = propertyRepo.findById(s).get();
      p.setCity(pty.getCity());
      p.setDescription(pty.getDescription());
      p.setPropertyName(pty.getPropertyName());
      p.setPropertyType(pty.getPropertyType());
      p.setPhotos(imgs);
      p.setNumberOfBathrooms(p.getNumberOfBathrooms());
      p.setNumberOfBedrooms(p.getNumberOfBedrooms());
      p.setState(p.getState());
      p.setZipCode(p.getZipCode());
      p.setStreetAddress(p.getStreetAddress());
      p.setRentAmount(p.getRentAmount());
      p.setOwnedBy(user);
      p.setSecurityDepositAmount(p.getSecurityDepositAmount());
      propertyRepo.save(p);
    }else{
      throw new CustomErrorException(HttpStatus.NOT_FOUND,"Property not found");
    }

  }


}
