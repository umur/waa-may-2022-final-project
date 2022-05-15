package com.pmp.server.controller;

import com.pmp.server.domain.Property;
import com.pmp.server.domain.PropertyRentalHistory;
import com.pmp.server.service.PropertyRentalHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/property-rental-histories")
@CrossOrigin
public class PropertyRentalHistoryController {

    private final PropertyRentalHistoryService propertyRentalHistoryService;

    public PropertyRentalHistoryController(PropertyRentalHistoryService propertyRentalHistoryService) {
        this.propertyRentalHistoryService = propertyRentalHistoryService;
    }

    @GetMapping("/{id}")
    private ResponseEntity<PropertyRentalHistory> getByID(@PathVariable UUID id){
        PropertyRentalHistory data = propertyRentalHistoryService.findById(id);
        return ResponseEntity.ok(data);
    }
}
