package com.propertymanagement.server.service;

import com.propertymanagement.server.dto.ImageDto;
import com.propertymanagement.server.dto.PropertyDto;
import com.propertymanagement.server.dto.RentDto;

import java.util.List;

public interface PropertyService extends CrudService<PropertyDto,PropertyDto,Long> {

    PropertyDto save(PropertyDto propertyDto, List<ImageDto> images);

    void rent(Long propertyId, RentDto rentDto);
}
