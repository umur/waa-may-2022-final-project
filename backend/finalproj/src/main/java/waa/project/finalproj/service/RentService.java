package waa.project.finalproj.service;

import waa.project.finalproj.dto.Rent.RentDTO;
import waa.project.finalproj.dto.Rent.RentSaveDTO;

import java.util.List;

public interface RentService {
    void add(RentSaveDTO t);
    void update(int id, RentDTO t);
    List<RentDTO> findAll();
    RentDTO findById(int id);
}
