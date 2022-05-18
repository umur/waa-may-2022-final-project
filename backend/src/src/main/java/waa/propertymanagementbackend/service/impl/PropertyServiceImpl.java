package waa.propertymanagementbackend.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waa.propertymanagementbackend.domain.Property;
import waa.propertymanagementbackend.domain.PropertyRentHistory;
import waa.propertymanagementbackend.domain.PropertyType;
import waa.propertymanagementbackend.domain.User;
import waa.propertymanagementbackend.dto.*;
import waa.propertymanagementbackend.repository.PropertyPhotosRep;
import waa.propertymanagementbackend.repository.PropertyRentHistoryRepo;
import waa.propertymanagementbackend.repository.PropertyRepository;
import waa.propertymanagementbackend.service.PropertyService;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService<PropertyDto> {
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyRentHistoryRepo propertyRentHistoryRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    PropertyPhotosRep propertyPhotosRep;

    List<PropertyDto> convertToPropertyDto(List<Property> properties) {

        Type listType = new TypeToken<List<PropertyDto>>() {
        }.getType();
        return modelMapper.map(properties, listType);

    }

    List<RentedPropertyDto> convertToRentedPropertyDto(List<PropertyRentHistory> properties) {

        Type listType = new TypeToken<List<RentedPropertyDto>>() {
        }.getType();
        return modelMapper.map(properties, listType);


    }


    @Override
    public void save(PropertyDto property) {
        Property p = new Property();
        modelMapper.map(property, p);

       // p.setSecurityDepositAmount(0);
        p.setVisible(true);
        p.setDeleted(false);
        System.out.println("hello");
        // int id = propertyRepository.getLastId();

        // p.setId(id + 1);
        //   System.out.println("id" + p.getId());
        propertyRepository.save(p);
//      for (int i = 0; i < property.getPropertyPhotos().size(); i++) {
//           propertyPhotosRep.save(property.getPropertyPhotos().get(i));
//        }
    }


    @Override
    public List<PropertyDto> getAll() {
        List<Property> properties = (List<Property>) propertyRepository.findAll();

        return convertToPropertyDto(properties);
    }

    /*Admin */
    @Override
    public List<RentedPropertyDto> getLastRented() {

        List<PropertyRentHistory> properties = propertyRentHistoryRepo.findByActiveOrderByRentedToAsc(true);
        return convertToRentedPropertyDto(properties);


    }

    /**/
    @Override
    public TotalIncomeDto getTotalIncomePerLocation(String city) {
        TotalIncomeDto dto = new TotalIncomeDto();
        dto.setIncome(propertyRentHistoryRepo.getTotalIncomePerLocation(city));
        return dto;
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
        p.setDeleted(value);
        propertyRepository.save(p);
    }

    @Override
    public void updatePropertyVisible(int id, boolean value) {
        Property p = new Property();
        p = propertyRepository.findById(id).get();
        p.setVisible(value);
        propertyRepository.save(p);
    }

    @Override
    public List<PropertyDto> findByOwnedByEmail(String email) {
        List<Property> properties = propertyRepository.findByOwnedByEmail(email);
        return convertToPropertyDto(properties);

    }

    @Override
    public List<PropertyDto> findByOwnedByEmailAndCity(String email, String city) {
        List<Property> properties = propertyRepository.findByOwnedByEmailAndAddressCity(email, city);
        return convertToPropertyDto(properties);
    }

    @Override
    public List<PropertyDto> findByLastRentedByEmail(String email) {
        List<Property> properties = propertyRepository.findByLastRentedByEmail(email);
        return convertToPropertyDto(properties);

    }

    @Override
    public TotalIncomeDto totalIncomePerLanLordAndCity(String email, String city) {
        TotalIncomeDto dto = new TotalIncomeDto();
        dto.setIncome(propertyRentHistoryRepo.getTotalIncomePerLocationAndLandlord(city, email));
        return dto;

    }


    @Override
    public List<RentedPropertyDto> getPropertiesLeasesInMonth(String email) {
        LocalDate date = LocalDate.now().plusDays(30);
        List<PropertyRentHistory> properties = propertyRentHistoryRepo.getPropertiesLeasesInMonth(email, date);
        return convertToRentedPropertyDto(properties);


    }


    @Override
    public List<PropertyDto> getByLandlordAndCityAndRoomsCount(String email, String city, int numberOfBedrooms) {

        List<Property> properties = propertyRepository.findByOwnedByEmailAndAddressCityAndNumberOfBedrooms(email, city, numberOfBedrooms);
        return convertToPropertyDto(properties);

    }

    @Override
    public List<PropertyDto> getByLandlordAndRoomsCount(String email, int numberOfBedrooms) {
        List<Property> properties = propertyRepository.findByOwnedByEmailAndNumberOfBedrooms(email, numberOfBedrooms);
        return convertToPropertyDto(properties);
    }

    public void changeVisibility(int id, boolean value) {
        Property p = new Property();
        p = propertyRepository.findById(id).get();
        p.setVisible(value);
        propertyRepository.save(p);
    }

    /**
     * Landlord
     */


    /**
     * for tenant Search
     **/
    @Override
    public List<PropertyDto> findByNumberOfBedroomsAndIsOccupied(int numberOfBedrooms, boolean isOccupied) {
        List<Property> properties = propertyRepository.findByNumberOfBedroomsAndIsOccupiedAndVisible(numberOfBedrooms, isOccupied, true);
        return convertToPropertyDto(properties);

    }

    @Override
    public List<PropertyDto> getByLandlordAndIsOccupied(String email, boolean isOccupied) {
        List<Property> properties = propertyRepository.findByOwnedByEmailAndIsOccupied(email, isOccupied);
        return convertToPropertyDto(properties);

    }

    @Override
    public List<PropertyDto> findByAddressCityAndNumberOfBedroomsAndIsOccupied(String city, int numberOfBedroom, boolean isOccupied) {
        List<Property> properties = propertyRepository.findByAddressCityAndNumberOfBedroomsAndIsOccupiedAndVisible(city, numberOfBedroom, isOccupied, true);
        return convertToPropertyDto(properties);

    }

    @Override
    public List<PropertyDto> findByAddressCityAndIsOccupied(String city, boolean isOccupied) {
        List<Property> properties = propertyRepository.findByAddressCityAndIsOccupiedAndVisible(city, isOccupied, true);
        return convertToPropertyDto(properties);

    }

    @Autowired
    PropertyTypesRepo propertyTypesRepo;

    @Override
    public List<PropertyType> getPropertyTypes() {
        return (List<PropertyType>)propertyTypesRepo.findAll();
    }

    /**
     * Tenant
     */
    @Override
    public void rentProperty(PropertyRentingDto pDto) {
        Property p = new Property();
        PropertyDto p2 = new PropertyDto();
        p = propertyRepository.findById(pDto.getProperty().getId()).get();

        PropertyRentHistory pH = new PropertyRentHistory();
        modelMapper.map(pDto, pH);
        pH.setActive(true);

        pH.setId(propertyRentHistoryRepo.getLastId() + 1);
        p.setIsOccupied(true);
        User u = new User();
        modelMapper.map(pDto.getRentedBy(), u);
        p.setLastRentedBy(u);
        propertyRepository.save(p);
        propertyRentHistoryRepo.save(pH);

    }
}
