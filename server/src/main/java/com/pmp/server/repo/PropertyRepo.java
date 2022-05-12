package com.pmp.server.repo;

import com.pmp.server.domain.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Repository
public interface PropertyRepo extends PagingAndSortingRepository<Property, UUID> {
//  List<Property> findAll(Pageable pageable);
//  List<Property> findAllByPropertyNameLikeIgnoreCaseOrStateLikeIgnoreCaseOrPropertyType(String propertyName, String state, String propertyType, Pageable pageable);
  //SPage<Property> findAllByPropertyName(String name, Pageable pageable);
}
