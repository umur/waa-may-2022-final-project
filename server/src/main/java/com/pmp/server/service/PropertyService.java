package com.pmp.server.service;

import com.pmp.server.domain.Property;
import com.pmp.server.domain.PropertyRentalHistory;
import com.pmp.server.dto.PropertyDTO;
import com.pmp.server.dto.RentDTO;
import com.pmp.server.dto.common.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PropertyService {
 Page<Property> findAll(Pageable pageable);

 Property getById(UUID id);

 PropertyRentalHistory rent(UUID id, RentDTO rentdto);

 Page<Property> findAllwithFilter(Pageable page, String loc, int r);

 Page<Property> findAllByOwner(Pageable page);

 Property save(PropertyDTO p);

 Page<Property> search(Pageable page, String s);

 void delete(UUID s);

 void update(PropertyDTO pty,UUID s);
//  List<Property> findPropertiesWithSorting(String field);
//  Page<Property> findPropertiesWithPagination(int offset, int pageSize);
//  Page<Property> findPropertiesWithPaginationAndSorting(int offset,int pageSize,String field);

 ResponseMessage activate(UUID id, Boolean isActive);
}
