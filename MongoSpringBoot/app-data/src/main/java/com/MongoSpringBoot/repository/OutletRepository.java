package com.MongoSpringBoot.repository;

import com.MongoSpringBoot.model.OutletEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OutletRepository extends MongoRepository<OutletEntity, Long> { }
