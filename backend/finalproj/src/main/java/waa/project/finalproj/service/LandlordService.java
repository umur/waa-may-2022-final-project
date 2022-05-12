package waa.project.finalproj.service;

import waa.project.finalproj.dto.landlord.LandlordDTO;
import waa.project.finalproj.dto.landlord.LandlordSaveDTO;

import java.util.List;

public interface LandlordService {
    void add(LandlordSaveDTO t);
    void delete(int id);
    void update(int id, LandlordDTO t);
    List<LandlordDTO> findAll();
    LandlordDTO findById(int id);
    List<LandlordDTO> findAllWhereDeletedAtNotNull();
}
