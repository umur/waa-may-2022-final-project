package waa.propertymanagementbackend.service.impl;

import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waa.propertymanagementbackend.domain.Property;
import waa.propertymanagementbackend.domain.Users;
import waa.propertymanagementbackend.dto.PropertyDto;
import waa.propertymanagementbackend.dto.UsersDto;
import waa.propertymanagementbackend.repository.UserRepository;
import waa.propertymanagementbackend.service.CrudService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements CrudService<UsersDto> {
    private ModelMapper modelMapper=new ModelMapper();
    @Autowired
    UserRepository userRepository;

    @Override
    public void save(UsersDto user) {
        Users u = new Users();
        modelMapper.map(user, u);
        userRepository.save(u);

    }

    @Override
    public List<UsersDto> getAll() {
        List<UsersDto> dtos = new ArrayList<>();

        List<Users> p = (List<Users>) userRepository.findAll();
        UsersDto dto = new UsersDto();
        for (int i = 0; i < p.size(); i++) {
            modelMapper.map(p.get(i), dto);
            dtos.add(dto);
        }
        return dtos;


    }

    @Override
    public UsersDto getById(int id) {
        UsersDto dto = new UsersDto();
        modelMapper.map(userRepository.findById(id).get(), dto);
        return dto;

    }

    @Override
    public void delete(int id, boolean value) {

        Users u = new Users();
        u = userRepository.findById(id).get();

        u.setDeleted(value);
        userRepository.save(u);


    }

}
