package com.property.controller;

import com.property.dto.PropertyDto;
import com.property.dto.request.Rent;
import com.property.dto.response.DailyCountDto;
import com.property.service.PropertyService;
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
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
@CrossOrigin
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping( consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
    @PreAuthorize("hasRole('LANDLORD')")
    public ResponseEntity<PropertyDto> create(@RequestPart("property") PropertyDto propertyDto,
                                              @RequestPart("files") List<MultipartFile> files) throws Exception {
        propertyDto = propertyService.save(propertyDto, files);
        return ResponseEntity.ok(propertyDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('LANDLORD')")
    public ResponseEntity<PropertyDto> update(@RequestBody PropertyDto propertyDto, @PathVariable Long id) {
        propertyDto = propertyService.update(propertyDto,id);
        return ResponseEntity.ok(propertyDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('LANDLORD')")
    public ResponseEntity<PropertyDto> delete(@PathVariable Long id) {
        propertyService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/images/{imageId}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long id, @PathVariable Long imageId) {
        ByteArrayOutputStream downloadInputStream = propertyService.findImage(id,imageId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(downloadInputStream.toByteArray());
    }

    @GetMapping("/filter-property-not-rented")
    public ResponseEntity<List<PropertyDto>> getPropertyNotRented() {
        var properties = propertyService.findNotRented();
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/filter-top-10-lease-in-month")
    @PreAuthorize("hasRole('LANDLORD')")
    public ResponseEntity<List<PropertyDto>> top10LeaseProperty() {
        var properties = propertyService.findLast10lease();
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/filter-last-10-rented")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PropertyDto>> top10RentedProperty() {
        var properties = propertyService.findLast10Rent();
        return ResponseEntity.ok(properties);
    }

    @PostMapping("/{id}/rents")
    @PreAuthorize("hasRole('TENANT')")
    public ResponseEntity<Void> rent(@PathVariable Long id, @RequestBody Rent rent) throws Exception {
        propertyService.rent(id,rent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PropertyDto>> findAllProperty() {
        return ResponseEntity.ok(propertyService.findAll());
    }

    @GetMapping("/filter-weekly-rented")
    public ResponseEntity<List<DailyCountDto>> findAllByRentedDate(){
        List<DailyCountDto> weeklyRentedCount = propertyService.findWeeklyRentedCount();
        return ResponseEntity.ok(weeklyRentedCount);

    }

    @GetMapping("/filter-property-by-type")
    public ResponseEntity<List<PropertyDto>> findAllByPropertyTypeContains(@RequestParam("type") String type){
        List<PropertyDto> propertyDtos = propertyService.findAllByPropertyTypeContains(type);
        return ResponseEntity.ok(propertyDtos);
    }

    @GetMapping("/filter-property-by-roomno")
    public ResponseEntity<List<PropertyDto>> findAllByNoOfBedRoom(@RequestParam int noofroom){
        List<PropertyDto> propertyDtos = propertyService.findAllByNoOfBedRoom(noofroom);
        return ResponseEntity.ok(propertyDtos);
    }

    @GetMapping("/filter-property-by-address")
    public ResponseEntity<List<PropertyDto>> findAllByAddress_StateAndAddress_City(@RequestParam(required = false) String state,
                                                                                                 @RequestParam(required = false) String city){
        List<PropertyDto> properties = propertyService.findAllByAddress_StateAndAddress_City(state, city);
        return ResponseEntity.ok(properties);
    }

}
