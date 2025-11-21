package com.MongoSpringBoot.service.mapper;

import com.MongoSpringBoot.DTOs.ProductDto;
import com.MongoSpringBoot.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mapping(target = "id", ignore = true)
    OrderItem toOrderItem(ProductDto dto);

}
