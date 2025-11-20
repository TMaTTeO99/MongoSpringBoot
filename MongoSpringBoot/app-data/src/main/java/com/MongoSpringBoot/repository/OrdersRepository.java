package com.MongoSpringBoot.repository;

import com.MongoSpringBoot.model.OrdersEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdersRepository extends MongoRepository<OrdersEntity, Long> { }
