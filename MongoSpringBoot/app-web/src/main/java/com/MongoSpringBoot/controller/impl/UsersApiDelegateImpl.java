package com.MongoSpringBoot.controller.impl;

import com.MongoSpringBoot.DTOs.UserSpecDto;
import com.MongoSpringBoot.DTOs.UsersDto;
import com.MongoSpringBoot.UsersApiDelegate;
import com.MongoSpringBoot.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/users")
public class UsersApiDelegateImpl implements UsersApiDelegate {

    private final UsersService usersService;

    @Override
    @GetMapping
    public ResponseEntity<UsersDto> getUser(@RequestParam("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("CONTROLLER UsersApiDelegateImpl getUser: (id: {})", id);
        }
        return ResponseEntity.ok(usersService.getUserById(id));
    }

    @Override
    @PostMapping(value = "/find-by-spec")
    public ResponseEntity<UsersDto> getUsersBySpecification(@RequestBody UserSpecDto spec) {

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    @PostMapping
    public ResponseEntity<UsersDto> addUser(UsersDto user) {
        if (log.isDebugEnabled()) {
            log.debug("CONTROLLER UsersApiDelegateImpl addUser: (user: {})", user);
        }
        return ResponseEntity.ok(usersService.addUser(user));
    }


}
