package waa.project.finalproj.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import waa.project.finalproj.dto.house.HouseDTO;
import waa.project.finalproj.dto.house.HouseSaveDTO;
import waa.project.finalproj.entity.House;
import waa.project.finalproj.repository.HouseRepository;
import waa.project.finalproj.service.HouseService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final ModelMapper modelMapper;

    @Override
    public void add(HouseSaveDTO t) {
        houseRepository.save(modelMapper.map(t, House.class));
    }

    @Override
    public void delete(int id) {
        var j = houseRepository.findById(id);
        if (j.isPresent()){
            j.get().setDeletedAt(LocalDate.now());
            houseRepository.save(j.get());
        }
    }

    @Override
    public void update(int id, HouseDTO t) {
        var j = houseRepository.findById(id);
        if (j.isPresent()){
            j.get().setName(t.getName());
            j.get().setStreet(t.getStreet());
            j.get().setCity(t.getCity());
            j.get().setState(t.getState());
            j.get().setZip(t.getZip());
            j.get().setNumberOfBedrooms(t.getNumberOfBedrooms());
            j.get().setNumberOfBathrooms(t.getNumberOfBathrooms());
            j.get().setRentAmount(t.getRentAmount());
            j.get().setSecurityDepositAmount(t.getSecurityDepositAmount());
            j.get().setOccupied(t.isOccupied());
            j.get().setListed(t.isListed());
            houseRepository.save(j.get());
        }
    }

    @Override
    public List<HouseDTO> findAll() {
        return StreamSupport
                .stream(houseRepository.findAll().spliterator(), false)
                .map(u -> modelMapper.map(u, HouseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public HouseDTO findById(int id) {
        var h = houseRepository.findById(id);

        return h.isPresent() && h.get().getDeletedAt() == null ? modelMapper.map(h.get(), HouseDTO.class) : null;

    }

    @Override
    public List<HouseDTO> findAllWhereDeletedAtNotNull() {
        return StreamSupport
                .stream(houseRepository.findAllByDeletedAtIsNull().spliterator(), false)
                .map(u -> modelMapper.map(u, HouseDTO.class))
                .collect(Collectors.toList());
    }
}
