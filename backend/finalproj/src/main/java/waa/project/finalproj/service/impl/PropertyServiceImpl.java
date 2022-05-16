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

    private final PropertyRepository houseRepository;
    private final ModelMapper modelMapper;

    @Override
    public void add(PropertySaveDTO t) {
        houseRepository.save(modelMapper.map(t, Property.class));
    }

    @Override
    public void delete(int id) {
        var j = houseRepository.findById(id);
        if (j.isPresent()){
            j.get().setDeletedAt(LocalDate.now());
            houseRepository.save(j.get());
        }
    }

    @Override
    public void update(int id, PropertyDTO t) {
        var j = houseRepository.findById(id);
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
            houseRepository.save(j.get());
        }
    }

    @Override
    public List<PropertyDTO> findAll() {
        return StreamSupport
                .stream(houseRepository.findAll().spliterator(), false)
                .map(u -> modelMapper.map(u, PropertyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PropertyDTO findById(int id) {
        var h = houseRepository.findById(id);

        return h.isPresent() && h.get().getDeletedAt() == null ? modelMapper.map(h.get(), PropertyDTO.class) : null;
    }

    @Override
    public List<PropertyDTO> findAllWhereDeletedAtNotNull() {
        return StreamSupport
                .stream(houseRepository.findAllByDeletedAtIsNull().spliterator(), false)
                .map(u -> modelMapper.map(u, PropertyDTO.class))
                .collect(Collectors.toList());
    }
}
