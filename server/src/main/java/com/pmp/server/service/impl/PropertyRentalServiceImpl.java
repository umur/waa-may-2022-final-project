package com.pmp.server.service.impl;

import com.pmp.server.domain.Property;
import com.pmp.server.domain.PropertyRentalHistory;
import com.pmp.server.repo.PropertyRentalHistoryRepo;
import com.pmp.server.service.PropertyRentalHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PropertyRentalServiceImpl implements PropertyRentalHistoryService {
    private final PropertyRentalHistoryRepo propertyRentalHistoryRepo;

    public PropertyRentalServiceImpl(PropertyRentalHistoryRepo propertyRentalHistoryRepo) {
        this.propertyRentalHistoryRepo = propertyRentalHistoryRepo;
    }

    @Override
    public PropertyRentalHistory findById(UUID id) {
        Optional<PropertyRentalHistory> data = propertyRentalHistoryRepo.findById(id);
        if(!data.isPresent()){
            return null;
        }
        return data.get();
    }

    @Override
    public Object findAll() {
        Iterable list =  propertyRentalHistoryRepo.findAll();
        return StreamSupport.stream(list.spliterator(), false).collect(Collectors.toList());
    }
}
