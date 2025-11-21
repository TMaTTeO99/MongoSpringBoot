package com.MongoSpringBoot.service.mapper;

import com.MongoSpringBoot.DTOs.OrderDto;
import com.MongoSpringBoot.model.OrdersEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {ProductMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrdersMapper {

    @Mapping(target = "id", source = "orderId")
    OrdersEntity toEntityFromDto(OrderDto orderDto);

    @Mapping(target = "orderId", source = "id")
    OrderDto toDtoFromEntity(OrdersEntity orderDto);

}
