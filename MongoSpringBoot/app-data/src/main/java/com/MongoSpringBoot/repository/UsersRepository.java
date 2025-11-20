package com.MongoSpringBoot.repository;


import com.MongoSpringBoot.model.UsersEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<UsersEntity, Long> {}
