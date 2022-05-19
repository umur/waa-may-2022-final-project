package com.propertymanagement.server.controller;

import com.propertymanagement.server.dto.ImageDto;
import com.propertymanagement.server.dto.PropertyDto;
import com.propertymanagement.server.dto.RentDto;
import com.propertymanagement.server.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
@CrossOrigin
public class PropertyController {

    private final PropertyService propertyService;

//    @PostMapping()
////    @PreAuthorize("hasRole('LANDLORD')")
//    public ResponseEntity<PropertyDto> create(@RequestBody PropertyDto propertyDto, @RequestBody List<ImageDto> images) throws Exception {
//        propertyDto = propertyService.save(propertyDto, images);
//        return ResponseEntity.ok(propertyDto);
//    }

    @PostMapping()
//    @PreAuthorize("hasRole('LANDLORD')")
    public ResponseEntity<PropertyDto> create(@RequestBody PropertyDto propertyDto) throws Exception {
        propertyDto = propertyService.save(propertyDto);
        return ResponseEntity.ok(propertyDto);
    }

    @GetMapping
    public ResponseEntity<List<PropertyDto>> findAll() {
        var propertyDtos = propertyService.findAll();
        return ResponseEntity.ok(propertyDtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<PropertyDto> findById(@PathVariable Long id) {
        var propertyDto = propertyService.findById(id);
        return ResponseEntity.ok(propertyDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('LANDLORD')")
    public ResponseEntity<PropertyDto> update(@RequestBody PropertyDto propertyDto, @PathVariable Long id) {
        propertyDto = propertyService.update(propertyDto,id);
        return ResponseEntity.ok(propertyDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('LANDLORD')")
    public ResponseEntity<PropertyDto> delete(@PathVariable Long id) {
        propertyService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filter-10-properties-leases-end-in-month")
//    @PreAuthorize("hasRole('LANDLORD')")
    public ResponseEntity<List<PropertyDto>> get10PropertiesLeaseEndInAMonth() {
        var properties = propertyService.get10PropertiesLeaseEndInAMonth();
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/filter-last-10-properties-rented")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PropertyDto>> getLast10PropertiesRented() {
        var properties = propertyService.getLast10PropertiesRented();
        return ResponseEntity.ok(properties);
    }

    @PostMapping("/{id}/rents")
    @PreAuthorize("hasRole('TENANT')")
    public ResponseEntity<Void> rent(@PathVariable Long id, @RequestBody RentDto rent) throws Exception {
        propertyService.rent(id,rent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filter-property-by-occupation")
    public ResponseEntity<List<PropertyDto>> getPropertyNotOccupied(){
        List<PropertyDto> propertyDtos = propertyService.getPropertyNotOccupied();
        return ResponseEntity.ok(propertyDtos);
    }

    @GetMapping("/filter-property-by-number-of-bedroom")
    public ResponseEntity<List<PropertyDto>> getPropertiesByNoOfBedRoom(@RequestParam int numberOfBedroom){
        List<PropertyDto> propertyDtos = propertyService.getPropertiesByNoOfBedRoom(numberOfBedroom);
        return ResponseEntity.ok(propertyDtos);
    }

    @GetMapping("/filter-properties-by-location")
    public ResponseEntity<List<PropertyDto>> getPropertiesByLocation(@RequestParam String state){
        List<PropertyDto> properties = propertyService.getPropertiesByLocation(state);
        return ResponseEntity.ok(properties);
    }

}
