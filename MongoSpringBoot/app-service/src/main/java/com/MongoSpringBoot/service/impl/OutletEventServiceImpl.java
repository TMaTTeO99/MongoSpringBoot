package com.MongoSpringBoot.service.impl;

import com.MongoSpringBoot.events.OutletEvent;
import com.MongoSpringBoot.model.OutletEntity;
import com.MongoSpringBoot.repository.OutletRepository;
import com.MongoSpringBoot.service.OutletEventService;
import com.MongoSpringBoot.service.OutletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OutletEventServiceImpl implements OutletEventService {

    private final OutletService outletService;

    @Override
    @Transactional
    public void handleOutletEvent(OutletEvent outletEvent) {

        if (log.isDebugEnabled()) {
            log.debug("OutletEventService handleOutletEvent: (outletEvent: {})", outletEvent);
        }

        String name = outletEvent.getName();
        String address = outletEvent.getAddress();

        if (name == null || address == null) {
            log.error("Invalid event field");
            return;
        }

        Optional<OutletEntity> existentOutletOpt = outletService.getOutletByNameAndAddress(name, address);
        if (existentOutletOpt.isPresent()) {
            log.error("Outlet already exists: " + existentOutletOpt.get());
            return;
        }

        outletService.saveOutlet(outletService.buildOutletFromEvent(outletEvent));

    }
}
