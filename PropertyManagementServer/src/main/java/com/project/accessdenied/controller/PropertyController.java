package com.project.accessdenied.controller;

import com.project.accessdenied.dto.Pid;
import com.project.accessdenied.dto.PropertyDto;
import com.project.accessdenied.dto.RentedDto;
import com.project.accessdenied.dto.TenantRentDto;
import com.project.accessdenied.entity.Property;
import com.project.accessdenied.service.PropertyService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public List<Property> getAll() {
        return propertyService.getAll();
    }

    @GetMapping("/owner/{id}")
    public List<Property> getAllByOwner(@PathVariable long id) {
        return propertyService.getByOwner(id);
    }


    @GetMapping("/{id}")
    public Property getById(@PathVariable long id) {
        return propertyService.getById(id);
    }

    @GetMapping("/lastten")
    public List<Property> getLastTenProperties() {
        return propertyService.getLastTenRented();
    }

    //ADMIN
    @GetMapping("/incomes/city/{id}")
    public float getTotalIncomePerCityLocation(@PathVariable long id) {
        return propertyService.getTotalIncomePerLocation(id);
    }

    @GetMapping("/incomes/user/{id}")
    public List<PropertyDto> getTotalIncomePerUser(@PathVariable long id) {
        return propertyService.getTotalIncomePerUser(id);
    }

    @GetMapping("/leases")
    public List<Property> getTenPropertiesEndInMonth(){

        return propertyService.getLeases();

    }

    @GetMapping("/num-of-properties")
    public List<RentedDto> getLastWeekRented() {
        return propertyService.getLastWeekRented();
    }
    @GetMapping("/num-of-properties/{id}")
    public List<RentedDto> getLastWeekRentedById(@PathVariable long id) {
        return propertyService.getLastWeekRentedByID(id);
    }

    @PostMapping
    public void save(@RequestBody Property property) {
        propertyService.save(property);
    }
    @PostMapping("/delete")
    public void delete(@RequestBody Pid pid) { //@RequestBody long id
        //System.out.println(pid);
       propertyService.deleteById(pid.getId());
    }
    //filter

    @PostMapping("/rent")
    public void rent(@RequestBody TenantRentDto tenantRentDto) {
        propertyService.rent(tenantRentDto);
    }

}
