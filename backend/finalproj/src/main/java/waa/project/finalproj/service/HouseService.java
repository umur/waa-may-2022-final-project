package waa.project.finalproj.service;

import waa.project.finalproj.dto.house.HouseDTO;
import waa.project.finalproj.dto.house.HouseSaveDTO;

import java.util.List;

public interface HouseService {
    void add(HouseSaveDTO t);
    void delete(int id);
    void update(int id, HouseDTO t);
    List<HouseDTO> findAll();
    HouseDTO findById(int id);
    List<HouseDTO> findAllWhereDeletedAtNotNull();
}
