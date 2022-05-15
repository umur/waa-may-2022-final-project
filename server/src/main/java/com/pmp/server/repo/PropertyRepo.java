package com.pmp.server.repo;

import com.pmp.server.domain.Property;
import com.pmp.server.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PropertyRepo extends PagingAndSortingRepository<Property, UUID> {
    Page<Property> findAllByCityIsLikeIgnoreCaseAndAndNumberOfBedroomsGreaterThanEqual(Pageable page, String loc, int room);
    Page<Property> findAllByOwnedBy(Pageable page, User u);
//  List<Property> findAllByPropertyNameLikeIgnoreCaseOrStateLikeIgnoreCaseOrPropertyType(String propertyName, String state, String propertyType, Pageable pageable);
  //SPage<Property> findAllByPropertyName(String name, Pageable pageable);

}
