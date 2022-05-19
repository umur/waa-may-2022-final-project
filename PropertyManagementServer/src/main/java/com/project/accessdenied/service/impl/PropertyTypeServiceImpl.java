package com.project.accessdenied.service.impl;

import com.project.accessdenied.entity.PropertyType;
import com.project.accessdenied.repository.PropertyTypeRepository;
import com.project.accessdenied.service.PropertyTypeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyTypeServiceImpl implements PropertyTypeService {
    private final PropertyTypeRepository propertyTypeRepository;

    public PropertyTypeServiceImpl(PropertyTypeRepository propertyTypeRepository) {
        this.propertyTypeRepository = propertyTypeRepository;
    }

    @Override
    public List<PropertyType> getAll() {
        var result = new ArrayList<PropertyType>();
        propertyTypeRepository.findAll().forEach(result::add);
        return result;
    }
}
