package com.MongoSpringBoot.service;

import com.MongoSpringBoot.events.ProductEvent;

public interface ProductEventService {

    void handleProductEvent(ProductEvent productEvent);
}
