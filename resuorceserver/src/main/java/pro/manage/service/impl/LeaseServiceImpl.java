package pro.manage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import pro.manage.entity.Lease;
import pro.manage.entity.dto.LeaseDto;

import pro.manage.repository.LeaseRepository;

import pro.manage.service.LeaseService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaseServiceImpl implements LeaseService {

    private final  LeaseRepository leaseRepository;
    @Override
    public LeaseRepository getRepo() {
        return leaseRepository;
    }

    @Override
    public Class<LeaseDto> getDTOType() {
        return LeaseDto.class;
    }

    @Override
    public Class<Lease> getTType() {
        return Lease.class;
    }


}

