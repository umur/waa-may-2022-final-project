package pro.manage.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pro.manage.entity.Address;
import pro.manage.entity.Property;

@Repository
public interface PropertyRepository extends PagingAndSortingRepository<Property, Long> {
    Property findByAddress(Address address);
}
