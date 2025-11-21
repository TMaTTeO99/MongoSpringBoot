package com.MongoSpringBoot.service;

import com.MongoSpringBoot.events.OutletEvent;
import com.MongoSpringBoot.model.OutletEntity;

import java.util.Optional;

public interface OutletService {

    OutletEntity getOutletById(Long id);
    Optional<OutletEntity> getOutletByNameAndAddress(String outletName, String address);
    OutletEntity buildOutletFromEvent(OutletEvent event);
    OutletEntity saveOutlet(OutletEntity outlet);
}
