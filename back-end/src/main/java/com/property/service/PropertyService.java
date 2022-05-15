package com.property.service;

import com.property.dto.PropertyDto;
import com.property.dto.request.Rent;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PropertyService extends CrudService<PropertyDto,PropertyDto,Long> {

    PropertyDto save(PropertyDto propertyDto, List<MultipartFile> files);

    void rent(Long propertyId, Rent rent);

    List<PropertyDto> findAllByPropertyTypeContains(String type);

    List<PropertyDto> findAllByNoOfBedRoom(int noOfBedRoom);
}
