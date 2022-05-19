package com.project.accessdenied.service;

import com.project.accessdenied.entity.PropertyType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PropertyTypeService {
    List<PropertyType> getAll();
}
