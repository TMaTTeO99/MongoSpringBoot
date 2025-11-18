package com.MongoSpringBoot.service.mapper;

import com.MongoSpringBoot.DTOs.UsersDto;
import com.MongoSpringBoot.model.UsersEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersDto toDtoFromUser(UsersEntity entity);
    UsersEntity toUserFromDto(UsersDto dto);
}
