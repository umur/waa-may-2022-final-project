package waa.project.finalproj.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import waa.project.finalproj.dto.property.PropertyDTO;
import waa.project.finalproj.dto.property.PropertySaveDTO;
import waa.project.finalproj.entity.Property;
import waa.project.finalproj.repository.PropertyRepository;
import waa.project.finalproj.service.PropertyService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final ModelMapper modelMapper;

    @Override
    public void add(PropertySaveDTO t) {
        propertyRepository.save(modelMapper.map(t, Property.class));
    }

    @Override
    public void delete(int id) {
        var j = propertyRepository.findById(id);
        if (j.isPresent()){
            j.get().setDeletedAt(LocalDate.now());
            propertyRepository.save(j.get());
        }
    }

    @Override
    public void update(int id, PropertyDTO t) {
        var j = propertyRepository.findById(id);
        if (j.isPresent()){
            j.get().setName(t.getName());
            j.get().setStreet(t.getStreet());
            j.get().setCity(t.getCity());
            j.get().setState(t.getState());
            j.get().setZip(t.getZip());
            j.get().setNumberOfBedrooms(t.getNumberOfBedrooms());
            j.get().setNumberOfBathrooms(t.getNumberOfBathrooms());
            j.get().setRentAmount(t.getRentAmount());
            j.get().setSecurityDepositAmount(t.getSecurityDepositAmount());
            j.get().setOccupied(t.isOccupied());
            j.get().setListed(t.isListed());
            propertyRepository.save(j.get());
        }
    }

    @Override
    public List<PropertyDTO> findAll() {
        return StreamSupport
                .stream(propertyRepository.findAll().spliterator(), false)
                .map(u -> modelMapper.map(u, PropertyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PropertyDTO findById(int id) {
        var h = propertyRepository.findById(id);

        return h.isPresent() && h.get().getDeletedAt() == null ? modelMapper.map(h.get(), PropertyDTO.class) : null;
    }

    @Override
    public List<PropertyDTO> findAllWhereDeletedAtNotNull() {
        return StreamSupport
                .stream(propertyRepository.findAllByDeletedAtIsNull().spliterator(), false)
                .map(u -> modelMapper.map(u, PropertyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyDTO> findTop10OrderByIdDesc() {
        return StreamSupport
                .stream(propertyRepository.findTop10ByOccupiedIsTrueAndDeletedAtIsNullOrderByIdDesc().spliterator(), false)
                .map(u -> modelMapper.map(u, PropertyDTO.class))
                .collect(Collectors.toList());
    }

}

