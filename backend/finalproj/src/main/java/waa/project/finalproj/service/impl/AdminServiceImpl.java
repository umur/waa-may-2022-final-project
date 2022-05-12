package waa.project.finalproj.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import waa.project.finalproj.dto.admin.AdminDTO;
import waa.project.finalproj.dto.admin.AdminSaveDTO;
import waa.project.finalproj.entity.Admin;
import waa.project.finalproj.repository.AdminRepository;
import waa.project.finalproj.service.AdminService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    @Override
    public void add(AdminSaveDTO adminDTO) {
        adminRepository.save(modelMapper.map(adminDTO, Admin.class));
    }

    @Override
    public void delete(int id) {
        var admin = adminRepository.findById(id);
        if (admin.isPresent()){
            admin.get().setDeletedAt(LocalDate.now());
            admin.get().getUser().setDeletedAt(LocalDate.now());
            adminRepository.save(admin.get());
        }
    }

    @Override
    public void update(int id, AdminDTO t) {
        var admin = adminRepository.findById(id);
        if (admin.isPresent()){
            admin.get().setActive(t.isActive());
            admin.get().setFirstname(t.getFirstname());
            admin.get().setLastname(t.getLastname());
            admin.get().getUser().setActive(t.getUser().isActive());
            admin.get().getUser().setPassword(t.getUser().getPassword());
            admin.get().getUser().setEmail(t.getUser().getEmail());
            admin.get().getUser().setLastLoggedInAt(t.getUser().getLastLoggedInAt());
            admin.get().getUser().setRole(t.getUser().getRole());
            adminRepository.save(admin.get());
        }
    }

    @Override
    public List<AdminDTO> findAll() {
        return StreamSupport
                .stream(adminRepository.findAll().spliterator(), false)
                .map(u -> modelMapper.map(u, AdminDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdminDTO findById(int id) {
        var admin = adminRepository.findById(id);

        return admin.isPresent() && admin.get().getDeletedAt() == null ? modelMapper.map(admin.get(), AdminDTO.class) : null;
    }

    @Override
    public List<AdminDTO> findAllWhereDeletedAtNotNull() {
        return StreamSupport
                .stream(adminRepository.findAllByDeletedAtIsNull().spliterator(), false)
                .map(u -> modelMapper.map(u, AdminDTO.class))
                .collect(Collectors.toList());
    }

}
