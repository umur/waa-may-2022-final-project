package waa.propertymanagementbackend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waa.propertymanagementbackend.domain.Property;
import waa.propertymanagementbackend.dto.PropertyDto;
import waa.propertymanagementbackend.service.CrudService;
import waa.propertymanagementbackend.repository.PropertyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyServiceImpl implements CrudService<PropertyDto> {
    @Autowired
    private PropertyRepository propertyRepository;

    private ModelMapper modelMapper=new ModelMapper();

    @Override
    public void save(PropertyDto property) {
        Property p = new Property();
        modelMapper.map(property, p);
        propertyRepository.save(p);

    }

    @Override
    public List<PropertyDto> getAll() {
        List<PropertyDto> dtos = new ArrayList<>();

        List<Property> p = (List<Property>) propertyRepository.findAll();
        PropertyDto dto = new PropertyDto();
        for (int i = 0; i < p.size(); i++) {
            modelMapper.map(p.get(i), dto);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public PropertyDto getById(int id) {


        PropertyDto dto = new PropertyDto();
        modelMapper.map(propertyRepository.findById(id).get(), dto);
        return dto;
    }

    @Override
    public void delete(int id, boolean value) {
        Property p = new Property();
        p = propertyRepository.findById(id).get();
        System.out.println("value" + value);
        System.out.println("prop" + p.getPropertyName());
        p.setDeleted(value);
        propertyRepository.save(p);
    }

    public void List(int id, boolean value) {
        Property p = new Property();
        p = propertyRepository.findById(id).get();
        p.setVisible(value);
        propertyRepository.save(p);
    }

}
