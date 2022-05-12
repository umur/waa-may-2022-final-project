package pro.manage.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.manage.entity.Role;

@Repository
public interface RoleRepository  extends CrudRepository<Role, Long> {

}
