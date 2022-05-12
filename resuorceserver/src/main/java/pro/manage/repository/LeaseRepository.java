package pro.manage.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.manage.entity.Lease;

@Repository
public interface LeaseRepository extends CrudRepository<Lease, Long> {
}
