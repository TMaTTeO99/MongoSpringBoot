package com.MongoSpringBoot.service.impl;

import com.MongoSpringBoot.events.OutletEvent;
import com.MongoSpringBoot.exception.EntityNotFoundException;
import com.MongoSpringBoot.model.OutletEntity;
import com.MongoSpringBoot.repository.OutletRepository;
import com.MongoSpringBoot.service.OutletService;
import com.MongoSpringBoot.service.mapper.OutletMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutletServiceImpl implements OutletService {

    private final OutletRepository outletRepository;
    private final OutletMapper outletMapper;

    @Override
    public OutletEntity getOutletById(Long id) {

        return outletRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Out not found: " + id));
    }

    @Override
    public Optional<OutletEntity> getOutletByNameAndAddress(String outletName, String address) {
        return outletRepository
                .findByNameAndAddress(outletName, address);
    }

    @Override
    public OutletEntity buildOutletFromEvent(OutletEvent event) {
        return outletMapper.toOutletEntityFromEvent(event);
    }

    @Override
    public OutletEntity saveOutlet(OutletEntity outlet) {
        return outletRepository.save(outlet);
    }
}
