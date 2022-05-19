package pro.manage.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pro.manage.entity.Address;
import pro.manage.entity.Property;

import java.util.UUID;

@Repository
public interface PropertyRepository extends PagingAndSortingRepository<Property, UUID> {
    Property findByAddress(Address address);
}
