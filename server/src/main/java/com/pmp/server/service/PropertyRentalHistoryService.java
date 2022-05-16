package com.pmp.server.service;

import com.pmp.server.domain.PropertyRentalHistory;

import java.util.List;
import java.util.UUID;

public interface PropertyRentalHistoryService {
    PropertyRentalHistory findById(UUID id);

    Object findAll();
}
