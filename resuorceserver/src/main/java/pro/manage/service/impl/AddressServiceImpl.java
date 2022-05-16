package pro.manage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.manage.entity.Address;
import pro.manage.entity.dto.AddressDto;
import pro.manage.repository.AddressRepository;
import pro.manage.service.AddressService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AddressServiceImpl  implements AddressService {
    private final  AddressRepository addressRepository;

    @Override
    public AddressRepository getRepo() {
        return addressRepository;
    }

    @Override
    public Class<AddressDto> getDTOType() {
        return AddressDto.class;
    }


    @Override
    public List<Address> findAllByStateAndCity(String state, String city) {
        return addressRepository.findAllByStateAndCity(state,city);
    }


}


