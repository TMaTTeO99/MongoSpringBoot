package com.MongoSpringBoot.service;

import com.MongoSpringBoot.DTOs.UsersDto;
import com.MongoSpringBoot.model.UsersEntity;

public interface UsersService {

    UsersEntity internalGetUserById(Long id);
    UsersEntity saveUser(UsersEntity user);
    UsersDto getUserById(Long id);
    UsersDto addUser(UsersDto user);
    UsersDto updateUser(Long id, UsersDto user);
    void deleteUser(Long id);


}
