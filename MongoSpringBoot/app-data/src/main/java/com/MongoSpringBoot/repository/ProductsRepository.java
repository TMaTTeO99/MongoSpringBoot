package com.MongoSpringBoot.repository;

import com.MongoSpringBoot.model.ProductsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductsRepository extends MongoRepository<ProductsEntity, Long> {
    Optional<ProductsEntity> findByNameAndDescription(String name, String description);
}
