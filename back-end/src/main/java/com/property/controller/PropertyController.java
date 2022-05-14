package com.property.controller;

import com.property.dto.PropertyDto;
import com.property.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping( consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<PropertyDto> create(@RequestPart("property") PropertyDto propertyDto,
                                              @RequestPart("files") List<MultipartFile> files) throws Exception {
        propertyDto = propertyService.save(propertyDto, files);
        return ResponseEntity.ok(propertyDto);
    }

}
