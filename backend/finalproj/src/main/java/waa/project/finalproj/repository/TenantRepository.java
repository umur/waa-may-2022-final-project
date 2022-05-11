package waa.project.finalproj.repository;

import org.springframework.data.repository.CrudRepository;
import waa.project.finalproj.model.Tenant;

public interface TenantRepository extends CrudRepository<Tenant, Integer> {
}
