package waa.project.finalproj.service;

import waa.project.finalproj.dto.property.PropertyDTO;
import waa.project.finalproj.dto.property.propertySaveDTO;

import java.util.List;

public interface PropertyService {
    void add(propertySaveDTO t);
    void delete(int id);
    void update(int id, PropertyDTO t);
    List<PropertyDTO> findAll();
    PropertyDTO findById(int id);
    List<PropertyDTO> findAllWhereDeletedAtNotNull();
}
