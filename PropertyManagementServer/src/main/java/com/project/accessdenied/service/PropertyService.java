package com.project.accessdenied.service;

import com.project.accessdenied.dto.PropertyDto;
import com.project.accessdenied.dto.RentedDto;
import com.project.accessdenied.dto.TenantRentDto;
import com.project.accessdenied.entity.Property;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface PropertyService {
    void save(Property p);
    void deleteById(long id);
    List<Property> getAll();
    Property getById(long id);
    List<Property> getAllByOccupiedIs(boolean b);
    List<Property> getAllByNumberOfBedroomsIsGreaterThanEqual(int roomNum);
    List<Property> getAllByCity(long id);
    List<Property> getAllByState(long id);
    //admin
    List<Property> getLastTenRented();
    float getTotalIncomePerLocation(long id);
    List<PropertyDto> getTotalIncomePerUser(long id);
    List<Property> getLeases();
    List<RentedDto> getLastWeekRented();

    List<RentedDto> getLastWeekRentedByID(long id);
    //landlord
    List<Property> getLeaseEndComing();

    void rent(TenantRentDto tenantRentDto);

    List<Property> getByOwner(Long id);


}
