package com.MongoSpringBoot.service.impl;

import com.MongoSpringBoot.exception.EntityNotFoundException;
import com.MongoSpringBoot.model.OutletEntity;
import com.MongoSpringBoot.repository.OutletRepository;
import com.MongoSpringBoot.service.OutletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutletServiceImpl implements OutletService {

    private final OutletRepository outletRepository;

    @Override
    public OutletEntity getOutletById(Long id) {

        return outletRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Out not found: " + id));
    }
}
