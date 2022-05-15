package com.property.service.impls;

import com.property.domain.Photo;
import com.property.domain.Property;
import com.property.domain.PropertyRent;
import com.property.dto.PropertyDto;
import com.property.dto.request.Rent;
import com.property.exception.custom.PropertyAlreadyRented;
import com.property.exception.custom.ResourceNotFoundException;
import com.property.respository.PropertyRentRepository;
import com.property.respository.PropertyRepository;
import com.property.respository.UserRepository;
import com.property.service.PropertyService;
import com.property.service.S3BucketStorageService;
import com.property.util.RandomAlphabet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    private final PropertyRentRepository propertyRentRepository;

    private final S3BucketStorageService s3BucketStorageService;

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    @Override
    public PropertyDto save(PropertyDto propertyDto) {
        Property property = modelMapper.map(propertyDto, Property.class);
        property = propertyRepository.save(property);
        return modelMapper.map(property,PropertyDto.class);
    }

    @Override
    public PropertyDto save(PropertyDto propertyDto, List<MultipartFile> files) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Login with user: {}",username);
        var user = userRepository.findByEmail(username);
        Property property = modelMapper.map(propertyDto, Property.class);
        property.setLandLord(user);
        if(files.size() > 0){
            var photoMetas = s3BucketStorageService.uploadFiles(RandomAlphabet.random(user.getId().toString()),files);
            var photos = photoMetas.stream().map(meta -> new Photo(meta.getUrl(),meta.getKeyName())).toList();
            property.addPhotos(photos);
        }
        property = propertyRepository.save(property);
        return modelMapper.map(property,PropertyDto.class);
    }

    @Override
    public void rent(Long propertyId, Rent rent) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new ResourceNotFoundException(String.format("Property with id: %s not found",propertyId)));
        Optional<PropertyRent> mayBePropertyRent = propertyRentRepository.findByPropertyIdAndRentEndDateIsAfter(propertyId, LocalDate.now());
        if(mayBePropertyRent.isPresent()){
            throw new PropertyAlreadyRented(String.format("Property with id:%s already rented",propertyId));
        }
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Login with user: {}",username);
        var user = userRepository.findByEmail(username);
        PropertyRent propertyRent = new PropertyRent(property,user, rent.getRentEndDate(), rent.getAmount());
        user.addTenantRent(propertyRent);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PropertyDto> findAll() {
        var properties = propertyRepository.findAll();
        Type listType = new TypeToken<List<PropertyDto>>(){}.getType();
        return modelMapper.map(properties,listType);
    }

    @Override
    @Transactional(readOnly = true)
    public PropertyDto findById(Long id) {
        var property = propertyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Property with id: %s not found",id)));
        return modelMapper.map(property,PropertyDto.class);
    }

    @Override
    public PropertyDto update(PropertyDto propertyDto, Long id) {
        propertyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Property with id: %s not found",id)));
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
    public List<PropertyDto> findAllByPropertyTypeContains(String type){
        var properties = propertyRepository.findAllByPropertyTypeContains(type);
        Type listType = new TypeToken<List<PropertyDto>>(){}.getType();
        return modelMapper.map(properties,listType);
    }

    @Override
    public List<PropertyDto> findAllByNoOfBedRoom(int noOfBedRoom){
        var properties = propertyRepository.findAllByNoOfBedRoom(noOfBedRoom);
        Type listType = new TypeToken<List<PropertyDto>>(){}.getType();
        return modelMapper.map(properties,listType);
    }
}
