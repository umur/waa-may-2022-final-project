package com.pmp.server.controller;

import com.pmp.server.domain.Property;
import com.pmp.server.dto.PropertyDTO;
import com.pmp.server.dto.RentDTO;
import com.pmp.server.dto.common.PagingResponse;
import com.pmp.server.dto.common.ResponseMessage;
import com.pmp.server.service.impl.PropertyServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/landlord")
@CrossOrigin
public class LandlordController {
    private final PropertyServiceImpl propertyService;

    public LandlordController(PropertyServiceImpl propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/properties")
    public PagingResponse getProperties(Pageable page,@RequestParam Optional<String> search) {
        if(search.isPresent()){
            Page<Property> list = propertyService.search(page,search.get());
            return new PagingResponse<Property>(list);
        }
        Page<Property> list = propertyService.findAllByOwner(page);
        return new PagingResponse<Property>(list);
    }
    @PostMapping("/properties")
    public ResponseMessage addProperties(@RequestBody PropertyDTO data) {
       propertyService.save(data);
        return new ResponseMessage("success", HttpStatus.CREATED);
    }
}
