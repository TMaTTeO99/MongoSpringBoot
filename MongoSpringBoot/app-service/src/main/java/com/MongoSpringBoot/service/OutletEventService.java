package com.MongoSpringBoot.service;

import com.MongoSpringBoot.events.OutletEvent;

public interface OutletEventService {

    void handleOutletEvent(OutletEvent outletEvent);

}
