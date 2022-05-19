package pro.manage.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pro.manage.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, UUID> {

    Optional<User> findByEmail(String email);
    List<User> findAllByDeletedAtIsNull();
    User findByResetPasswordToken(String token);

    List<User> findTop10ByRoleAndDeletedAtIsNullOrderByIdDesc(String role);

}
