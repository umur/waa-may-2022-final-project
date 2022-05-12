package pro.manage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.manage.entity.Address;

import pro.manage.entity.Property;

import pro.manage.entity.dto.PropertyDto;

import pro.manage.repository.PropertyRepository;

import pro.manage.service.PropertyService;


@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private PropertyRepository propertyRepository;

    @Override
    public PropertyRepository getRepo() {
        return propertyRepository;
    }

    @Override
    public Class<PropertyDto> getDTOType() {
        return PropertyDto.class;
    }

    @Override
    public Property findByAddress(Address address)
    {
        return propertyRepository.findByAddress(address);
    }



 /*
    public House updateHouse(House editedHouse, long id) {

        return houseRepository.findById(id)
         .map(house->{
          house.setPrice(editedHouse.getPrice());
          house.setHouseType(editedHouse.getHouseType());
          house.setNumberOfRooms(editedHouse.getNumberOfRooms());
          house.setDescription(editedHouse.getDescription());
         // house.setAddress(editedHouse.getAddress());
          return houseRepository.save(house);
         }).orElseGet(()->{
             return houseRepository.save(editedHouse);
                });


    }

  */


}
