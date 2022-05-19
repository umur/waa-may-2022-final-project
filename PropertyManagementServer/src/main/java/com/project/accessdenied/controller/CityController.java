package com.project.accessdenied.controller;

import com.project.accessdenied.dto.CityDto;
import com.project.accessdenied.entity.City;
import com.project.accessdenied.service.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> getAll() {
        return cityService.getAll();
    }

    @GetMapping("/{id}")
    public City getById(@PathVariable long id) {
        return cityService.getById(id);
    }

    @GetMapping("/incomes/{id}")
    public List<CityDto> getAllIncomesByCity(@PathVariable long id) {
        return cityService.getAllCitiesIncome(id);
    }

    @GetMapping("/state/{id}")
    public List<City> getAllByState(@PathVariable long id) {
        return cityService.getByState(id);
    }
}
