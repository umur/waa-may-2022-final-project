package pro.manage.service;
import pro.manage.entity.Role;
import pro.manage.entity.dto.RoleDto;
import pro.manage.repository.RoleRepository;

public interface RoleService extends GenericService<Role, RoleDto,Long, RoleRepository> {

}
