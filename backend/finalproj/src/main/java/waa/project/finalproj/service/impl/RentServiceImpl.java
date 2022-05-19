package waa.project.finalproj.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import waa.project.finalproj.dto.Rent.RentDTO;
import waa.project.finalproj.dto.Rent.RentSaveDTO;
import waa.project.finalproj.entity.Property;
import waa.project.finalproj.entity.Rent;
import waa.project.finalproj.entity.User;
import waa.project.finalproj.repository.RentRepository;
import waa.project.finalproj.service.RentService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;
    private final ModelMapper modelMapper;

    @Override
    public void add(RentSaveDTO t) {
        rentRepository.save(modelMapper.map(t, Rent.class));
    }

    @Override
    public void update(int id, RentDTO t) {
        var j = rentRepository.findById(id);
        if (j.isPresent()){
            j.get().setEndDate(t.getEndDate());
            j.get().setStartDate(t.getStartDate());
            j.get().setUser(modelMapper.map(t.getUser(), User.class));
            j.get().setProperty(modelMapper.map(t.getProperty(), Property.class));
            rentRepository.save(j.get());
        }
    }

    @Override
    public List<RentDTO> findAll() {
        return StreamSupport
                .stream(rentRepository.findAll().spliterator(), false)
                .map(u -> modelMapper.map(u, RentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RentDTO findById(int id) {
        var h = rentRepository.findById(id);
        return h.isPresent() ? modelMapper.map(h.get(), RentDTO.class) : null;

    }


}
