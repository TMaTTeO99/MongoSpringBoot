package com.MongoSpringBoot.service;

import com.MongoSpringBoot.DTOs.OrderDto;

public interface OrdersService {

    OrderDto getOrderById(Long id);
    OrderDto addOrder(Long userId, Long outletId, OrderDto order);

}
