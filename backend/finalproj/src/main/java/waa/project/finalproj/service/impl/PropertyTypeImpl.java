package waa.project.finalproj.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import waa.project.finalproj.dto.propertyType.PropertyTypeDTO;
import waa.project.finalproj.entity.PropertyType;
import waa.project.finalproj.repository.PropertyTypeRepository;
import waa.project.finalproj.service.PropertyTypeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class PropertyTypeImpl implements PropertyTypeService {

    private final PropertyTypeRepository propertyTypeRepository;
    private final ModelMapper modelMapper;

    @Override
    public void add(PropertyTypeDTO t) {
        propertyTypeRepository.save(modelMapper.map(t, PropertyType.class));
    }

    @Override
    public void delete(int id) {
        var j = propertyTypeRepository.findById(id);
        if (j.isPresent()){
            j.get().setDeletedAt(LocalDateTime.now());
            propertyTypeRepository.save(j.get());
        }
    }

    @Override
    public void update(int id, PropertyTypeDTO t) {
        var j = propertyTypeRepository.findById(id);
        if (j.isPresent()){
            j.get().setName(t.getName());
            propertyTypeRepository.save(j.get());
        }
    }

    @Override
    public List<PropertyTypeDTO> findAll() {
        return StreamSupport
                .stream(propertyTypeRepository.findAll().spliterator(), false)
                .map(u -> modelMapper.map(u, PropertyTypeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PropertyTypeDTO findById(int id) {
        var h = propertyTypeRepository.findById(id);
        return h.isPresent() && h.get().getDeletedAt() == null ? modelMapper.map(h.get(), PropertyTypeDTO.class) : null;

    }

    @Override
    public List<PropertyTypeDTO> findAllWhereDeletedAtNotNull() {
        return StreamSupport
                .stream(propertyTypeRepository.findAllByDeletedAtIsNull().spliterator(), false)
                .map(u -> modelMapper.map(u, PropertyTypeDTO.class))
                .collect(Collectors.toList());
    }
}
