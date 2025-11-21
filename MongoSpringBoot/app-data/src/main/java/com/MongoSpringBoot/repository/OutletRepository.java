package com.MongoSpringBoot.repository;

import com.MongoSpringBoot.model.OutletEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OutletRepository extends MongoRepository<OutletEntity, Long> {

    Optional<OutletEntity> findByNameAndAddress(String outletName, String address);

}
