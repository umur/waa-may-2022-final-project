package waa.propertymanagementbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import waa.propertymanagementbackend.domain.PropertyRentHistory;
import waa.propertymanagementbackend.dto.PropertyDto;
import waa.propertymanagementbackend.dto.PropertyRentingDto;
import waa.propertymanagementbackend.dto.RentedPropertyDto;
import waa.propertymanagementbackend.service.PropertyService;


import java.util.List;

@RestController
@RequestMapping("/api/properties")
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
     * Landlord Operations
     */

//    @PostMapping("landlord/pictures")
//    public void addPictures(@RequestBody PropertyDto p) {}

    @PostMapping("landlord")
    public void addProperty(@RequestBody PropertyDto p) {

        service.save(p);
    }

    @PutMapping("/landLord")
    public void update(@RequestBody PropertyDto p) {
        service.save(p);
    }

    @GetMapping("/landLord/ownedBy/{email}/{city}")
    public List<PropertyDto> getOwnedByAndCity(@PathVariable String email, @PathVariable String city) {
        return service.findByOwnedByEmailAndCity(email, city);
    }

    @GetMapping("/landLord/ownedBy/{email}")
    public List<PropertyDto> getOwnedBy(@PathVariable String email) {
        return service.findByOwnedByEmail(email);
    }


    @DeleteMapping("/landLord/{id}/{value}")
    public void delete(@PathVariable int id, @PathVariable Boolean value) {
        service.delete(id, value);
    }

    @PutMapping("/landLord/{id}/{value}")
    public void updateVisible(@PathVariable int id, @PathVariable Boolean value) {
        service.updatePropertyVisible(id, value);
    }


    @GetMapping("/landLord/leases/{email}")
    public List<RentedPropertyDto> getPropertiesLeasesInMonth(@PathVariable String email) {
        return service.getPropertiesLeasesInMonth(email);
    }

    @GetMapping("/landLord/totalIncome/{email}/{city}")
    public float totalIncomePerLanLordAndCity(@PathVariable String email, @PathVariable String city) {
        return service.totalIncomePerLanLordAndCity(email, city);
    }

    @GetMapping("/landLord/addressAndNumberOfRooms/{email}/{city}/{numberOfBedrooms}")
    public List<PropertyDto> getByLandLordAndCityAndRoomsCount(@PathVariable String email, @PathVariable String city, @PathVariable int numberOfBedrooms) {
        return service.getByLandLordAndCityAndRoomsCount(email, city, numberOfBedrooms);
    }

    @GetMapping("/landLord/numberOfRooms/{email}/{numberOfBedrooms}")
    public List<PropertyDto> getByLandLordAndRoomsCount(@PathVariable String email, @PathVariable int numberOfBedrooms) {
        return service.getByLandLordAndRoomsCount(email, numberOfBedrooms);
    }

    @GetMapping("landLord/occupied/{email}/{isOccupied}")
    public List<PropertyDto> getByLandLordAndIsOccupied(@PathVariable String email, @PathVariable Boolean isOccupied) {
        return service.getByLandLordAndIsOccupied(email, isOccupied);
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
    public float totalIncome(@PathVariable String city) {
        return service.getTotalIncomePerLocation(city);
    }


}
