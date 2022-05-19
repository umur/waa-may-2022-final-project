package com.project.accessdenied.service.impl;

import com.project.accessdenied.dto.PropertyDto;
import com.project.accessdenied.dto.RentedDto;
import com.project.accessdenied.dto.TenantRentDto;
import com.project.accessdenied.entity.Property;
import com.project.accessdenied.repository.PropertyRepository;
import com.project.accessdenied.repository.UserRepository;
import com.project.accessdenied.service.PropertyService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository, UserRepository userRepository) {
        this.propertyRepository = propertyRepository;

        this.userRepository = userRepository;
    }


    @Override
    public void save(Property p) {
        propertyRepository.save(p);
    }

    @Override
    public void deleteById(long id) {
        var property = propertyRepository.findById(id).get();
        property.setIsDeleted(true);
        save(property);
    }

    @Override
    public List<Property> getAll() {
        var result = new ArrayList<Property>();
       propertyRepository.findAllByIsOccupiedEqualsAndIsDeletedEquals(false, false).forEach(result::add);
        //propertyRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Property getById(long id) {

        return propertyRepository.findById(id).get();
    }

    @Override
    public List<Property> getAllByOccupiedIs(boolean b) {
        return null;
        //return propertyRepository.findAllByOccupiedIs(true);
    }

    @Override
    public List<Property> getAllByNumberOfBedroomsIsGreaterThanEqual(int rn) {
       return null;
        // return propertyRepository.findAllByNumberOfBedroomsIsGreaterThanEqual(rn);
    }

    @Override
    public List<Property> getAllByCity(long id) {
        return null;
        //City c=cityService.getById(id);
       // return propertyRepository.findAllByCity(c);
    }

    @Override
    public List<Property> getAllByState(long id) {
        return  null;
       // State s= stateService.getById(id);
       // return propertyRepository.findAllByState(s);
    }

    @Override
    public List<Property> getLastTenRented() {
        /*LocalDate now= LocalDate.now();
        return propertyRepository.findAll().stream().sorted(new Comparator<Property>() {
            @Override
            public int compare(Property o1, Property o2) {
                LocalDate o1min=o1.getRentPeriods().stream().map(rp->rp.getRentedAt()).min(Comparator.comparingLong(date -> ChronoUnit.DAYS.between(now , date))).orElse(LocalDate.of(1900, 1, 1));
                LocalDate o2min=o2.getRentPeriods().stream().map(rp->rp.getRentedAt()).min(Comparator.comparingLong(date -> ChronoUnit.DAYS.between(now , date))).orElse(LocalDate.of(1900, 1, 1));

                return (int) ChronoUnit.DAYS.between(o1min , o2min);
            }
        }).limit(10).collect(Collectors.toList());
*/
        boolean val = true;
        return propertyRepository.findTop10ByIsOccupiedEqualsOrderByLastRentedDateDesc(true);
    }

    @Override
    public float getTotalIncomePerLocation(long id) {
        var result = new ArrayList<Property>();
        propertyRepository.findAllByIsOccupiedEqualsAndCity_IdEquals(true, id).forEach(result::add);

        float sum = 0;

        for(int i = 0; i<result.size(); i++) {
            sum += result.get(i).getRentAmount();
        }

        return sum;
    }

    @Override
    public List<PropertyDto> getTotalIncomePerUser(long id) {
        var entity = propertyRepository.findAllByOwnedBy_IdEquals(id);

        var result = new ArrayList<PropertyDto>();
        entity.forEach(p -> {
            PropertyDto pro = new PropertyDto();

            pro.setName(p.getName());

            LocalDate first = p.getLastRentedDate();
            LocalDate last = p.getEndDate();

            float amount = 0;

            if(first != null && last != null) {
                long days = DAYS.between(first, last);
                amount = days * p.getRentAmount();
            }
            pro.setTotalAmount(amount);

            result.add(pro);

        });

        return result;
    }

    @Override
    public List<Property> getLeases() {
        LocalDate today = LocalDate.now();
        LocalDate nextMonth = today.plusMonths(1);
        return propertyRepository.findAllByIsOccupiedEqualsAndEndDateGreaterThanAndEndDateLessThan(true, today,nextMonth);
    }

    @Override
    public List<RentedDto> getLastWeekRented() {
        LocalDate today = LocalDate.now();

        LocalDate lastWeek = today.minusDays(7);

        var entity = propertyRepository.findAllByLastRentedDateGreaterThanAndLastRentedDateLessThan(lastWeek, today);

        var result = new ArrayList<RentedDto>();

        Map<String, Integer> dayMap = new HashMap<>();

        entity.forEach(e -> {
            DayOfWeek day = e.getLastRentedDate().getDayOfWeek();
            String dayOfWeek = day.name();
            if(dayMap.get(dayOfWeek) == null) {
                dayMap.put(dayOfWeek, 1);
            } else {
                int res = dayMap.get(dayOfWeek);
                res++;
                dayMap.put(dayOfWeek, res);
            }
        });

        for (String day :dayMap.keySet()) {
            int count = dayMap.get(day);
            RentedDto dto = new RentedDto();
            dto.setDay(day);
            dto.setRented(count);
            result.add(dto);
        }

        return result;
    }

    @Override
    public List<RentedDto> getLastWeekRentedByID(long id) {

            LocalDate today = LocalDate.now();

            LocalDate lastWeek = today.minusDays(7);

            var entity = propertyRepository.findAllByOwnedBy_IdEqualsAndLastRentedDateGreaterThanAndLastRentedDateLessThan(id,lastWeek, today);

            var result = new ArrayList<RentedDto>();

            Map<String, Integer> dayMap = new HashMap<>();

            entity.forEach(e -> {
                DayOfWeek day = e.getLastRentedDate().getDayOfWeek();
                String dayOfWeek = day.name();
                if(dayMap.get(dayOfWeek) == null) {
                    dayMap.put(dayOfWeek, 1);
                } else {
                    int res = dayMap.get(dayOfWeek);
                    res++;
                    dayMap.put(dayOfWeek, res);
                }
            });

            for (String day :dayMap.keySet()) {
                int count = dayMap.get(day);
                RentedDto dto = new RentedDto();
                dto.setDay(day);
                dto.setRented(count);
                result.add(dto);
            }

            return result;
        }


    @Override
    public List<Property> getLeaseEndComing() {
        /*LocalDate endOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        return propertyRepository.findAll().stream().sorted(new Comparator<Property>() {
            @Override
            public int compare(Property o1, Property o2) {
                LocalDate o1min=o1.getRentPeriods().stream().map(rp->rp.getRentedAt()).min(Comparator.comparingLong(date -> ChronoUnit.DAYS.between(endOfMonth , date))).orElse(LocalDate.of(1900, 1, 1));
                LocalDate o2min=o2.getRentPeriods().stream().map(rp->rp.getRentedAt()).min(Comparator.comparingLong(date -> ChronoUnit.DAYS.between(endOfMonth , date))).orElse(LocalDate.of(1900, 1, 1));

                return (int) ChronoUnit.DAYS.between(o1min , o2min);
            }
        }).limit(10).collect(Collectors.toList());

         */
        return null;
    }

    @Override
    public void rent(TenantRentDto tenantRentDto) {
        var property = propertyRepository.findById(tenantRentDto.getPid()).get();
        var user = userRepository.findById(tenantRentDto.getId()).get();

        property.setLastRentedBy(user);
        property.setLastRentedDate(LocalDate.now());
        property.setEndDate(tenantRentDto.getEndDate());
        property.setOccupied(Boolean.TRUE);

        save(property);

    }

    @Override
    public List<Property> getByOwner(Long id) {
        return propertyRepository.findAllByOwnedBy_IdEquals(id);
    }




}
