package waa.project.finalproj.service;

import waa.project.finalproj.dto.admin.AdminDTO;
import waa.project.finalproj.dto.admin.AdminSaveDTO;

import java.util.List;

public interface AdminService {
    void add(AdminSaveDTO t);
    void delete(int id);
    void update(int id, AdminDTO t);
    List<AdminDTO> findAll();
    AdminDTO findById(int id);
    List<AdminDTO> findAllWhereDeletedAtNotNull();
}
