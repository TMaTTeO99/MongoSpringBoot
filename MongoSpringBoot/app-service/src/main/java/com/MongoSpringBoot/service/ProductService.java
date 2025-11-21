package com.MongoSpringBoot.service;

import com.MongoSpringBoot.events.ProductEvent;
import com.MongoSpringBoot.model.ProductsEntity;

import java.util.Optional;

public interface ProductService {

    Optional<ProductsEntity> getProductByNameAndDescription(String name, String description);
    ProductsEntity saveProduct(ProductsEntity product);

    ProductsEntity toProductFromEvent(ProductEvent event);

}
