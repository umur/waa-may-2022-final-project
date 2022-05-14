package com.property.service;

import com.property.dto.PropertyDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PropertyService extends CrudService<PropertyDto,PropertyDto,Long> {

    PropertyDto save(PropertyDto propertyDto, List<MultipartFile> files);
}
