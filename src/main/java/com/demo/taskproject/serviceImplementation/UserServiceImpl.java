package com.demo.taskproject.serviceImplementation;

import com.demo.taskproject.entity.Users;
import com.demo.taskproject.payload.UserDto;
import com.demo.taskproject.repository.UserRepository;
import com.demo.taskproject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {

        Users user = DtoToEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Users savedUser = userRepo.save(user);

        return EntityToDto(savedUser);
    }

    private Users DtoToEntity(UserDto userDto) {
        Users users = new Users();
        users.setName(userDto.getName());
        users.setEmail(userDto.getEmail());
        users.setPassword(userDto.getPassword());

        return users;
    }

    private UserDto EntityToDto(Users savedUser) {
        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setName(savedUser.getName());
        userDto.setEmail(savedUser.getEmail());
        userDto.setPassword(savedUser.getPassword());

        return userDto;
    }

    @Override
    public UserDto getUser(String name) {
        return EntityToDto(userRepo.findByUserName(name));
    }


}
