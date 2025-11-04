package com.demo.taskproject.service;

import com.demo.taskproject.payload.UserDto;

public interface UserService {

    public UserDto createUser(UserDto user);

    public UserDto getUser(String name);
}
