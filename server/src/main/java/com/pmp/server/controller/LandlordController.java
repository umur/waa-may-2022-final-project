package com.pmp.server.controller;

import com.google.common.base.CaseFormat;
import com.pmp.server.domain.Property;
import com.pmp.server.dto.NotificationDTO;
import com.pmp.server.dto.PropertyDTO;
import com.pmp.server.dto.Top10PropertyLeaseEndDTO;
import com.pmp.server.dto.common.PagingResponse;
import com.pmp.server.dto.common.ResponseMessage;
import com.pmp.server.service.impl.PropertyServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/landlord")
@CrossOrigin
public class LandlordController {
    private final SimpMessagingTemplate template;
    private final PropertyServiceImpl propertyService;

    public LandlordController(SimpMessagingTemplate template, PropertyServiceImpl propertyService) {
        this.template = template;
        this.propertyService = propertyService;
    }

    @GetMapping("/properties")
    public PagingResponse getProperties(Pageable page, @RequestParam Optional<String> search) {
        if(search.isPresent()){
            PageRequest daoPageable = PageRequest.of(
                    page.getPageNumber(),
                    page.getPageSize(),
                    convertDtoSortToDaoSort(page.getSort())
            );
            Page<Property> list = propertyService.search(daoPageable, search.get());
            return new PagingResponse<Property>(list);
        }
        Page<Property> list = propertyService.findAllByOwner(page);
        return new PagingResponse<Property>(list);
    }

    @GetMapping("/properties/{id}")
    public ResponseMessage getProperties(@PathVariable UUID id) {
        Property data = propertyService.getById(id);
        return new ResponseMessage("success", HttpStatus.OK,data);
    }
    @PostMapping("/properties")
    public ResponseMessage addProperties(@RequestBody PropertyDTO data) {
        Property p = propertyService.save(data);
        this.template.convertAndSend("/topic/tenants", new NotificationDTO("sai","New properties has been added!th"));
        return new ResponseMessage("success", HttpStatus.CREATED, p);
    }
    @PutMapping("/properties/{id}")
    public ResponseMessage updateProperties(@RequestBody PropertyDTO data,@PathVariable UUID id) {
        propertyService.update(data,id);
        return new ResponseMessage("success", HttpStatus.OK);
    }

    @PutMapping("/properties/{id}/activate")
    public ResponseMessage activate(@PathVariable UUID id) {
        return propertyService.activate(id, true);
    }

    @PutMapping("/properties/{id}/deactivate")
    public ResponseMessage deactivate(@PathVariable UUID id) {
        return propertyService.activate(id, false);
    }

    @DeleteMapping("/properties/{id}")
    public ResponseMessage delete(@PathVariable UUID id) {
        propertyService.delete(id);
        return new ResponseMessage("deleted", HttpStatus.OK);
    }


    private Sort convertDtoSortToDaoSort(Sort dtoSort) {
        return Sort.by(dtoSort.get()
                .map(sortOrder -> sortOrder.withProperty(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sortOrder.getProperty())))
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/properties/top10-lease-end")
    public ResponseMessage getTop10LeaseEnd(@RequestBody Top10PropertyLeaseEndDTO body) {
        var result = propertyService.top10LeaseEnd(body);
        return result;
    }
}
