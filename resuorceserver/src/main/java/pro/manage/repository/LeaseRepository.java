package pro.manage.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pro.manage.entity.Lease;

import java.util.UUID;

@Repository
public interface LeaseRepository extends PagingAndSortingRepository<Lease, UUID> {
}
