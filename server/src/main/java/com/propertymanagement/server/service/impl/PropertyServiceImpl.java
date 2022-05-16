package com.propertymanagement.server.service.impl;

import com.propertymanagement.server.domain.Image;
import com.propertymanagement.server.domain.Property;
import com.propertymanagement.server.domain.RentalActivity;
import com.propertymanagement.server.dto.ImageDto;
import com.propertymanagement.server.dto.PropertyDto;
import com.propertymanagement.server.dto.RentDto;
import com.propertymanagement.server.exception.PropertyIsRentedException;
import com.propertymanagement.server.exception.PropertyNotFoundException;
import com.propertymanagement.server.repository.PropertyRepository;
import com.propertymanagement.server.repository.UserRepository;
import com.propertymanagement.server.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    private final RentalActivityRepository rentalActivityRepository;

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    @Override
    public PropertyDto save(PropertyDto propertyDto) {
        Property property = modelMapper.map(propertyDto, Property.class);
        property = propertyRepository.save(property);
        return modelMapper.map(property,PropertyDto.class);
    }

    @Override
    public PropertyDto save(PropertyDto propertyDto, List<ImageDto> imageDtos) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findByEmail(username);
        Property property = modelMapper.map(propertyDto, Property.class);

        List<Image> images = new ArrayList<>();
        property.setOwnedBy(user);
        if(imageDtos.size() > 0){
            for(ImageDto imageDto : imageDtos) {
                Image image = modelMapper.map(imageDto, Image.class);
                images.add(image);
            }
            property.addImages(images);

        }
        property = propertyRepository.save(property);
        return modelMapper.map(property,PropertyDto.class);
    }

    @Override
    public List<PropertyDto> findAll() {
        var properties = propertyRepository.findAll();
        Type listType = new TypeToken<List<PropertyDto>>(){}.getType();
        return modelMapper.map(properties,listType);
    }

    @Override
    public PropertyDto findById(Long id) {
        Optional<Property> data = propertyRepository.findById(id);
        if(!data.isPresent()){
            throw new PropertyNotFoundException("Property not found");
        }
        return modelMapper.map(data,PropertyDto.class);
    }

    @Override
    public PropertyDto update(PropertyDto propertyDto, Long id) {
        Optional<Property> data = propertyRepository.findById(id);
        if(!data.isPresent()){
            throw new PropertyNotFoundException("Property not found");
        }
        Property property = modelMapper.map(propertyDto, Property.class);
        property.setId(id);
        property = propertyRepository.save(property);
        return modelMapper.map(property, PropertyDto.class);
    }

    @Override
    public void deleteById(Long id) {
        propertyRepository.deleteById(id);
    }

    @Override
    public void rent(Long propertyId, RentDto rentDto) {
        Optional<Property> data = propertyRepository.findById(propertyId);
        if(!data.isPresent()){
            throw new PropertyNotFoundException("Property not found");
        }
        Optional<RentalActivity> isRented = rentalActivityRepository.findByPropertyIdAndEndDateIsAfter(propertyId, LocalDate.now());
        if(isRented.isPresent()){
            throw new PropertyIsRentedException("Property is rented");
        }
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepository.findByEmail(username);
        RentalActivity rentalActivity = new RentalActivity();
        rentalActivity.setProperty(data.get());
        rentalActivity.setTenant(user);
        rentalActivity.setEndDate(rentDto.getEndDate());
        user.addRentedProperties(rentalActivity);
        userRepository.save(user);
    }
}
