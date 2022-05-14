package com.pmp.server.service;

import com.pmp.server.domain.Property;
import com.pmp.server.dto.RentDTO;
import com.pmp.server.dto.common.PagingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PropertyService {
 Page<Property> findAll(Pageable pageable);

 Property getById(UUID id);

 void rent(UUID id,RentDTO rentdto);

 Page<Property> findAllwithFilter(Pageable page, String loc, int r);
//  List<Property> findPropertiesWithSorting(String field);
//  Page<Property> findPropertiesWithPagination(int offset, int pageSize);
//  Page<Property> findPropertiesWithPaginationAndSorting(int offset,int pageSize,String field);
}
