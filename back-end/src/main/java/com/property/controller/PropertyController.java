package com.property.controller;

import com.property.dto.PropertyDto;
import com.property.dto.request.Rent;
import com.property.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping( consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
    @PreAuthorize("hasRole('LANDLORD')")
    public ResponseEntity<PropertyDto> create(@RequestPart("property") PropertyDto propertyDto,
                                              @RequestPart("files") List<MultipartFile> files) throws Exception {
        propertyDto = propertyService.save(propertyDto, files);
        return ResponseEntity.ok(propertyDto);
    }

    @PostMapping("/{id}/rents")
    @PreAuthorize("hasRole('TENANT')")
    public ResponseEntity<Void> rent(@PathVariable Long id, @RequestBody Rent rent) throws Exception {
        propertyService.rent(id,rent);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
