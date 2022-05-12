package waa.project.finalproj.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import waa.project.finalproj.dto.landlord.LandlordDTO;
import waa.project.finalproj.dto.landlord.LandlordSaveDTO;
import waa.project.finalproj.entity.Landlord;
import waa.project.finalproj.repository.LandlordRepository;
import waa.project.finalproj.service.LandlordService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class LandlordServiceImpl implements LandlordService {

    private final ModelMapper modelMapper;
    private final LandlordRepository landlordRepository;

    @Override
    public void add(LandlordSaveDTO t) {
        landlordRepository.save(modelMapper.map(t, Landlord.class));
    }

    @Override
    public void delete(int id) {
        var l = landlordRepository.findById(id);
        if (l.isPresent()){
            l.get().setDeletedAt(LocalDate.now());
            l.get().getUser().setDeletedAt(LocalDate.now());
            landlordRepository.save(l.get());
        }
    }

    @Override
    public void update(int id, LandlordDTO t) {
        var l = landlordRepository.findById(id);
        if (l.isPresent()){
            l.get().setActive(t.isActive());
            l.get().setFirstname(t.getFirstname());
            l.get().setLastname(t.getLastname());
            l.get().getUser().setActive(t.getUser().isActive());
            l.get().getUser().setPassword(t.getUser().getPassword());
            l.get().getUser().setEmail(t.getUser().getEmail());
            l.get().getUser().setLastLoggedInAt(t.getUser().getLastLoggedInAt());
            l.get().getUser().setRole(t.getUser().getRole());
            landlordRepository.save(l.get());
        }
    }

    @Override
    public List<LandlordDTO> findAll() {
        return StreamSupport
                .stream(landlordRepository.findAll().spliterator(), false)
                .map(u -> modelMapper.map(u, LandlordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public LandlordDTO findById(int id) {
        var l = landlordRepository.findById(id);

        return l.isPresent() && l.get().getDeletedAt() == null ? modelMapper.map(l.get(), LandlordDTO.class) : null;

    }

    @Override
    public List<LandlordDTO> findAllWhereDeletedAtNotNull() {
        return StreamSupport
                .stream(landlordRepository.findAllByDeletedAtIsNull().spliterator(), false)
                .map(u -> modelMapper.map(u, LandlordDTO.class))
                .collect(Collectors.toList());
    }
}
