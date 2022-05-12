package waa.project.finalproj.service;

import waa.project.finalproj.dto.tenant.TenantDTO;
import waa.project.finalproj.dto.tenant.TenantSaveDTO;

import java.util.List;

public interface TenantService {
    void add(TenantSaveDTO t);
    void delete(int id);
    void update(int id, TenantDTO t);
    List<TenantDTO> findAll();
    TenantDTO findById(int id);
    List<TenantDTO> findAllWhereDeletedAtNotNull();
}
