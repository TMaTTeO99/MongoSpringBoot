package com.MongoSpringBoot.service.impl;

import com.MongoSpringBoot.DTOs.OrderDto;
import com.MongoSpringBoot.repository.OrdersRepository;
import com.MongoSpringBoot.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;


    @Override
    public OrderDto getOrderById(Long id) {

        //TODO: to be implemented
        return null;
    }
}
