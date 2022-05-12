package pro.manage.service;
import pro.manage.entity.Address;
import pro.manage.entity.dto.AddressDto;
import pro.manage.repository.AddressRepository;

import java.util.List;

public interface AddressService  extends GenericService<Address, AddressDto,Long, AddressRepository>{
    List<Address> findAllByStateAndCity(String state, String city);

}

