package com.MongoSpringBoot.service;

import com.MongoSpringBoot.DTOs.UsersDto;

public interface UsersService {

    UsersDto getUserById(Long id);
    UsersDto addUser(UsersDto user);
    UsersDto updateUser(Long id, UsersDto user);
    void deleteUser(Long id);

}
