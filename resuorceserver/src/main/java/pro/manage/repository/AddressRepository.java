package pro.manage.repository;


import org.springframework.data.repository.CrudRepository;
import pro.manage.entity.Address;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address,Long> {
    List<Address> findAllByStateAndCity(String state, String city);
}
