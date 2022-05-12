package waa.project.finalproj.repository;

import org.springframework.data.repository.CrudRepository;
import waa.project.finalproj.entity.Tenant;

import java.util.List;

public interface TenantRepository extends CrudRepository<Tenant, Integer> {
    List<Tenant> findAllByDeletedAtIsNull();
}
