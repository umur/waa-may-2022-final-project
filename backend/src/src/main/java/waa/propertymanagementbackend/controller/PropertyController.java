package waa.propertymanagementbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import waa.propertymanagementbackend.domain.PropertyRentHistory;
import waa.propertymanagementbackend.domain.PropertyType;
import waa.propertymanagementbackend.dto.PropertyDto;
import waa.propertymanagementbackend.dto.PropertyRentingDto;
import waa.propertymanagementbackend.dto.RentedPropertyDto;
import waa.propertymanagementbackend.dto.TotalIncomeDto;
import waa.propertymanagementbackend.service.PropertyService;


import java.util.List;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin
public class PropertyController {


    @Autowired
    PropertyService<PropertyDto> service;

    @GetMapping
    public List<PropertyDto> getAll() {

        return service.getAll();
    }

//    @GetMapping("/{id}")
//    public PropertyDto getPropertyById(@PathVariable int id) {
//        return service.getById(id);
//    }


    /*
     * landlord Operations
     */

//    @PostMapping("landlord/pictures")
//    public void addPictures(@RequestBody PropertyDto p) {}

    @PostMapping("landlord")
    public void addProperty(@RequestBody PropertyDto p) {

        service.save(p);
    }

    @PutMapping("/landlord")
    public void update(@RequestBody PropertyDto p) {
        service.save(p);
    }

    @GetMapping("/landlord/ownedBy/{email}/{city}")
    public List<PropertyDto> getOwnedByAndCity(@PathVariable String email, @PathVariable String city) {
        return service.findByOwnedByEmailAndCity(email, city);
    }

    @GetMapping("/propertyTypes")
    public List<PropertyType> getPropertyTypes() {
      return  service.getPropertyTypes();
    }
    @GetMapping("/landlord/ownedBy/{email}")
    public List<PropertyDto> getOwnedBy(@PathVariable String email) {
        return service.findByOwnedByEmail(email);
    }

    @CrossOrigin
    @DeleteMapping("/landlord/{id}/{value}")
    public void delete(@PathVariable int id, @PathVariable Boolean value) {
        service.delete(id, value);
    }

    @PutMapping("/landlord/{id}/{value}")
    public void updateVisible(@PathVariable int id, @PathVariable Boolean value) {
        service.updatePropertyVisible(id, value);
    }


    @GetMapping("/landlord/leases/{email}")
    public List<RentedPropertyDto> getPropertiesLeasesInMonth(@PathVariable String email) {
        return service.getPropertiesLeasesInMonth(email);
    }

    @GetMapping("/landlord/totalIncome/{email}/{city}")
    public TotalIncomeDto totalIncomePerLanLordAndCity(@PathVariable String email, @PathVariable String city) {
        return service.totalIncomePerLanLordAndCity(email, city);
    }

    @GetMapping("/landlord/addressAndNumberOfRooms/{email}/{city}/{numberOfBedrooms}")
    public List<PropertyDto> getByLandlordAndCityAndRoomsCount(@PathVariable String email, @PathVariable String city, @PathVariable int numberOfBedrooms) {
        return service.getByLandlordAndCityAndRoomsCount(email, city, numberOfBedrooms);
    }

    @GetMapping("/landlord/numberOfRooms/{email}/{numberOfBedrooms}")
    public List<PropertyDto> getByLandlordAndRoomsCount(@PathVariable String email, @PathVariable int numberOfBedrooms) {
        return service.getByLandlordAndRoomsCount(email, numberOfBedrooms);
    }

    @GetMapping("landlord/occupied/{email}/{isOccupied}")
    public List<PropertyDto> getByLandlordAndIsOccupied(@PathVariable String email, @PathVariable Boolean isOccupied) {
        return service.getByLandlordAndIsOccupied(email, isOccupied);
    }

    /**
     * Tenant Operations
     */

    @PostMapping("/tenant")
    public void rentProperty(@RequestBody PropertyRentingDto p) {
        service.rentProperty(p);
    }

    @GetMapping("tenant/filterByCity/{city}/{isOccupied}")
    public List<PropertyDto> findByAddressCityAndIsOccupied(@PathVariable String city, @PathVariable Boolean isOccupied) {

        return service.findByAddressCityAndIsOccupied(city, isOccupied);
    }


    @GetMapping("/tenant/cityAndRoomsCount/{city}/{numberOfBedrooms}/{isOccupied}")
    public List<PropertyDto> findByCityAndRoomsCountAndIsOccupied(@PathVariable String city, @PathVariable int numberOfBedrooms, @PathVariable Boolean isOccupied) {

        return service.findByAddressCityAndNumberOfBedroomsAndIsOccupied(city, numberOfBedrooms, isOccupied);
    }

    @GetMapping("tenant/roomsCount/{numberOfBedrooms}/{isOccupied}")
    public List<PropertyDto> findByNumberOfBedroomsAndIsOccupied(@PathVariable int numberOfBedrooms, @PathVariable Boolean isOccupied) {

        return service.findByNumberOfBedroomsAndIsOccupied(numberOfBedrooms, isOccupied);
    }

    @GetMapping("/tenant/lastRentedBy/{email}")
    public List<PropertyDto> getRentedBy(@PathVariable String email) {

        return service.findByLastRentedByEmail(email);
    }

    /**
     * Admin Operations
     */
    @GetMapping("/admin/")
    public List<RentedPropertyDto> getLastRented() {
        return service.getLastRented();
    }

    @GetMapping("/admin/totalIncome/{city}")
    public TotalIncomeDto totalIncome(@PathVariable String city) {
        return service.getTotalIncomePerLocation(city);
    }


}
