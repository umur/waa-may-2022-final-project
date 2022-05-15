package pro.manage.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pro.manage.entity.Address;

import java.util.List;

public interface AddressRepository extends PagingAndSortingRepository<Address,Long> {
    List<Address> findAllByStateAndCity(String state, String city);
}
