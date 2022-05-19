package com.project.accessdenied.service.impl;

import com.project.accessdenied.dto.CityDto;
import com.project.accessdenied.entity.City;
import com.project.accessdenied.repository.CityRepository;
import com.project.accessdenied.service.CityService;
import com.project.accessdenied.service.PropertyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final PropertyService propertyService;

    public CityServiceImpl(CityRepository cityRepository, PropertyService propertyService) {
        this.cityRepository = cityRepository;
        this.propertyService = propertyService;
    }

    @Override
    public List<City> getAll() {
        var result = new ArrayList<City>();
        cityRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public City getById(long id) {
        return cityRepository.findById(id).get();
    }

    @Override
    public List<City> getByState(long id) {
        return cityRepository.findAllByState_Id(id);
    }

    @Override
    public float totalIncomeOfCity(long id) {
        return 0;
        //return getById(id).getState().stream().map(s->s.getProperties().stream().filter(p->p.isOccupied()==true).map(p->p.getRentAmount()).reduce(0, (a, b) -> a + b)).reduce(0, (a, b) -> a + b);
    }

    @Override
    public List<CityDto> getAllCitiesIncome(long id) {
        List<City> cities = cityRepository.findAllByState_Id(id);

        var result = new ArrayList<CityDto>();
        for (City c: cities) {
            float sum = propertyService.getTotalIncomePerLocation(c.getId());
            if(sum != 0) {
                CityDto dto = new CityDto();
                dto.setName(c.getName());
                dto.setTotal(sum);
                result.add(dto);
            }

        }

        return result;
    }
}
