package waa.propertymanagementbackend.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;
import waa.propertymanagementbackend.domain.EmailDataDetail;
import waa.propertymanagementbackend.domain.Property;
import waa.propertymanagementbackend.domain.User;
import waa.propertymanagementbackend.dto.EmailDataDetailDto;
import waa.propertymanagementbackend.dto.PropertyDto;
import waa.propertymanagementbackend.dto.UserDto;
import waa.propertymanagementbackend.repository.EmailDataDetailRep;
import waa.propertymanagementbackend.repository.UserRepository;
import waa.propertymanagementbackend.service.UserService;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService<UserDto> {
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    UserRepository userRepository;
    List<UserDto> convertToPropertyDto(List<User> users) {

        Type listType = new TypeToken<List<UserDto>>(){}.getType();
        return modelMapper.map(users,listType);

    }



  @Override
    public void save(UserDto user) {
        User u = new User();
        modelMapper.map(user, u);
        userRepository.save(u);

    }




/*
    @Override
    public List<User> getAll() {
        List<User> dtos = new ArrayList<>();

        List<User> p = (List<User>) userRepository.findAll();
        User dto = new User();
        for (int i = 0; i < p.size(); i++) {
            modelMapper.map(p.get(i), dto);
            dtos.add(dto);
        }
        return dtos;


    }

    @Override
    public User getById(int id) {
        User dto = new User();
        modelMapper.map(userRepository.findById(id).get(), dto);
        return dto;

    }

    @Override
    public void delete(int id, boolean value) {

        User u = new User();
        u = userRepository.findById(id).get();

        u.setDeleted(value);
        userRepository.save(u);


    }
*/

    /**
     * Admin
     *
     * @return
     */
    @Override
    public List<UserDto> getLastRecentTenants() {

        List<User> users = userRepository.getLastRecentTenants();

        List<UserDto> dtos = new ArrayList<>();
        UserDto dto = new UserDto();
        users.stream().forEach(item -> {
            modelMapper.map(item, dto);
            dtos.add(dto);
        });

        return dtos;

    }


    @Override
    public UserDto getById(int id) {
        UserDto dto = new UserDto();
        modelMapper.map(userRepository.findById(id).get(), dto);
        return dto;

    }
    @Override
    public UserDto getUserDtoByEmail(String email){
        User u = userRepository.findByEmail(email);
        UserDto dto = new UserDto();
        modelMapper.map(u, dto);
        return dto;

    }

    @Override
    public List<UserDto> getUserDtoByRole(String roleName) {
        return convertToPropertyDto( userRepository.findByRoleDescription(roleName));
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).get();

    }

    @Override
    public void activate(String email, boolean value) {
        User u = userRepository.findByEmail(email);
        u.setActive(value);
        userRepository.save(u);
    }






}
