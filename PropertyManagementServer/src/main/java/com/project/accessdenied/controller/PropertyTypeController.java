package com.project.accessdenied.controller;

import com.project.accessdenied.entity.PropertyType;
import com.project.accessdenied.service.PropertyTypeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/property-types")
public class PropertyTypeController {
    private final PropertyTypeService propertyTypeService;

    public PropertyTypeController(PropertyTypeService propertyTypeService) {
        this.propertyTypeService = propertyTypeService;
    }

    @GetMapping
    public List<PropertyType> getAll() {
        return propertyTypeService.getAll();
    }

}
