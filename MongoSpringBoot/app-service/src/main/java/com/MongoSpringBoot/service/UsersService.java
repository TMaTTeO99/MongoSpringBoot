package com.MongoSpringBoot.service;

import com.MongoSpringBoot.DTOs.UsersDto;

public interface UsersService {

    UsersDto getUserById(Long id);
    UsersDto addUser(UsersDto user);

}
