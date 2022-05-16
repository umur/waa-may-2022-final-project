package pro.manage.service;
import pro.manage.entity.Role;
import pro.manage.entity.dto.RoleDto;
import pro.manage.repository.RoleRepository;

import java.util.UUID;

public interface RoleService extends GenericService<Role, RoleDto, UUID, RoleRepository> {

}
