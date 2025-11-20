package com.MongoSpringBoot.repository;

import com.MongoSpringBoot.model.ProductsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsRepository extends MongoRepository<ProductsEntity, Long> {}
