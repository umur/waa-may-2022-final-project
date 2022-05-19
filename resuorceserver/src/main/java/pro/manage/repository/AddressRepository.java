package pro.manage.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pro.manage.entity.Address;

import java.util.List;
import java.util.UUID;

public interface AddressRepository extends PagingAndSortingRepository<Address, UUID> {
    List<Address> findAllByStateAndCity(String state, String city);
}
