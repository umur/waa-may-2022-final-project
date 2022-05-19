package waa.project.finalproj.service;

import waa.project.finalproj.dto.propertyType.PropertyTypeDTO;

import java.util.List;

public interface PropertyTypeService {
    void add(PropertyTypeDTO t);
    void delete(int id);
    void update(int id, PropertyTypeDTO t);
    List<PropertyTypeDTO> findAll();
    PropertyTypeDTO findById(int id);
    List<PropertyTypeDTO> findAllWhereDeletedAtNotNull();
}
