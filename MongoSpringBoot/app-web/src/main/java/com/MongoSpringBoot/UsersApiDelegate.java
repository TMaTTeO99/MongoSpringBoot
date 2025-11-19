package com.MongoSpringBoot;

import com.MongoSpringBoot.DTOs.UserSpecDto;
import com.MongoSpringBoot.DTOs.UsersDto;
import org.springframework.http.ResponseEntity;

public interface UsersApiDelegate {

    ResponseEntity<UsersDto> getUser(Long id);

    ResponseEntity<UsersDto> getUsersBySpecification(UserSpecDto spec);

    ResponseEntity<UsersDto> addUser(UsersDto user);

    ResponseEntity<UsersDto> updateUser(Long id, UsersDto user);

    ResponseEntity<Void> deleteUser(Long id);
}
