package pro.manage.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.manage.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {




}
