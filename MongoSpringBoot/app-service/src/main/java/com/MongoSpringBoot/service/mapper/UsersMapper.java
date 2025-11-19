package com.MongoSpringBoot.service.mapper;

import com.MongoSpringBoot.DTOs.UsersDto;
import com.MongoSpringBoot.model.UsersEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersDto toDtoFromUser(UsersEntity entity);
    UsersEntity toUserFromDto(UsersDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(UsersDto dto, @MappingTarget UsersEntity target);

}
