package waa.project.finalproj.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import waa.project.finalproj.dto.landlord.LandlordDTO;
import waa.project.finalproj.dto.tenant.TenantDTO;
import waa.project.finalproj.dto.tenant.TenantSaveDTO;
import waa.project.finalproj.entity.Tenant;
import waa.project.finalproj.repository.TenantRepository;
import waa.project.finalproj.service.TenantService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;
    private final ModelMapper modelMapper;

    @Override
    public void add(TenantSaveDTO t) {
        tenantRepository.save(modelMapper.map(t, Tenant.class));
    }

    @Override
    public void delete(int id) {
        var l = tenantRepository.findById(id);
        if (l.isPresent()){
            l.get().setDeletedAt(LocalDate.now());
            l.get().getUserId().setDeletedAt(LocalDate.now());
            tenantRepository.save(l.get());
        }
    }

    @Override
    public void update(int id, TenantDTO t) {
        var l = tenantRepository.findById(id);
        if (l.isPresent()){
            l.get().setActive(t.isActive());
            l.get().setFirstname(t.getFirstname());
            l.get().setLastname(t.getLastname());
            l.get().getUserId().setActive(t.getUser().isActive());
            l.get().getUserId().setPassword(t.getUser().getPassword());
            l.get().getUserId().setEmail(t.getUser().getEmail());
            l.get().getUserId().setLastLoggedInAt(t.getUser().getLastLoggedInAt());
            l.get().getUserId().setRole(t.getUser().getRole());
            tenantRepository.save(l.get());
        }
    }

    @Override
    public List<TenantDTO> findAll() {
        return StreamSupport
                .stream(tenantRepository.findAll().spliterator(), false)
                .map(u -> modelMapper.map(u, TenantDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TenantDTO findById(int id) {
        var l = tenantRepository.findById(id);

        return l.isPresent() && l.get().getDeletedAt() == null ? modelMapper.map(l.get(), TenantDTO.class) : null;

    }

    @Override
    public List<TenantDTO> findAllWhereDeletedAtNotNull() {
        return StreamSupport
                .stream(tenantRepository.findAllByDeletedAtIsNull().spliterator(), false)
                .map(u -> modelMapper.map(u, TenantDTO.class))
                .collect(Collectors.toList());
    }
}
