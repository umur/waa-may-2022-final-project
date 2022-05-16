package waa.propertymanagementbackend.service;


import waa.propertymanagementbackend.dto.PropertyRentingDto;
import waa.propertymanagementbackend.dto.RentedPropertyDto;

import java.util.List;

public interface PropertyService <TDto>{
    void save(TDto dto);

    List<TDto> getAll();

    TDto getById(int id);

    public void delete(int id,boolean value);
    public  void updatePropertyVisible(int id,boolean value);
    public List<TDto> findByOwnedByEmailAndCity(String email,String city);
    public List<TDto> findByOwnedByEmail(String email);
    public List<TDto> findByLastRentedByEmail(String email);
    public  float totalIncomePerLanLordAndCity(String email,String city);
    public  float getTotalIncomePerLocation(String city);
    //  public  List<TDto> findByOwnedByEmailAndRentedToIsLessThanEqual(String email);
    public  List<RentedPropertyDto> getPropertiesLeasesInMonth(String email);
    public  List<TDto> findByAddressCityAndIsOccupied(String city,boolean isOccupied);

    public  List<TDto> getByLandlordAndCityAndRoomsCount(String email,String city,int numberOfBedrooms);
    public  List<TDto> getByLandlordAndRoomsCount(String email,int numberOfBedrooms);
    public  List<TDto> getByLandlordAndIsOccupied(String email, boolean isOccupied);
    public  List<TDto> findByNumberOfBedroomsAndIsOccupied(int numberOfBedrooms,boolean isOccupied);
    public  List<TDto> findByAddressCityAndNumberOfBedroomsAndIsOccupied(String city,int numberOfBedroom,boolean isOccupied);
    public void rentProperty(PropertyRentingDto PDto);


    /**
     * Admin Operations
     */
    public List<RentedPropertyDto> getLastRented();
}
