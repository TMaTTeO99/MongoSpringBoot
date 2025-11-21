package com.MongoSpringBoot.service.mapper;

import com.MongoSpringBoot.DTOs.ProductDto;
import com.MongoSpringBoot.events.ProductEvent;
import com.MongoSpringBoot.model.OrderItem;
import com.MongoSpringBoot.model.ProductsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mapping(target = "id", ignore = true)
    OrderItem toOrderItem(ProductDto dto);

    ProductsEntity toProductEntityFromEvent(ProductEvent event);

}
