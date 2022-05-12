package waa.propertymanagementbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import waa.propertymanagementbackend.domain.Property;
import waa.propertymanagementbackend.dto.PropertyDto;
import waa.propertymanagementbackend.service.CrudService;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private CrudService<PropertyDto> service;

    @GetMapping
    public List<PropertyDto> getAll() {
        return service.getAll();
    }

    @PostMapping()
    public void save(@RequestBody PropertyDto p) {
        service.save(p);
    }

    @DeleteMapping("/{id}/{value}")
    public void delete(@PathVariable int id, @PathVariable Boolean value) {
        service.delete(id, value);
    }

    @GetMapping("/{id}")
    public PropertyDto getPropertyById(@PathVariable int id) {
        return service.getById(id);
    }
}
