package com.MongoSpringBoot.service.impl;

import com.MongoSpringBoot.DTOs.UsersDto;
import com.MongoSpringBoot.exception.EntityNotFoundException;
import com.MongoSpringBoot.model.UsersEntity;
import com.MongoSpringBoot.service.UsersService;
import com.MongoSpringBoot.repository.UsersRepository;
import com.MongoSpringBoot.service.mapper.UsersMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    @Override
    public UsersDto getUserById(Long id) {

        if (log.isDebugEnabled()) {
            log.debug("UsersServiceImpl getUserById: (id: {})", id);
        }

        UsersEntity user = usersRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user not found: " + id));

        return usersMapper.toDtoFromUser(user);

    }

    @Override
    public UsersDto addUser(UsersDto user) {

        if (log.isDebugEnabled()) {
            log.debug("UsersServiceImpl addUser: (user: {})", user);
        }

        UsersEntity savedUser = usersRepository.save(usersMapper.toUserFromDto(user));
        return usersMapper.toDtoFromUser(savedUser);
    }

    @Override
    public UsersDto updateUser(Long id, UsersDto user) {

        if (log.isDebugEnabled()) {
            log.debug("UsersServiceImpl updateUser: (id: {}, user: {})", id, user);
        }

        UsersEntity userEntity = usersRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));

        usersMapper.update(user, userEntity);
        UsersEntity savedUser = usersRepository.save(userEntity);

        return usersMapper.toDtoFromUser(savedUser);
    }

    @Override
    public void deleteUser(Long id) {

        if (log.isDebugEnabled()) {
            log.debug("UsersServiceImpl deleteUser: (id: {})", id);
        }

        usersRepository.deleteById(id);
    }

}
