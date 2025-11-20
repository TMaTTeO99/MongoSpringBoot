package com.MongoSpringBoot.controller.impl;

import com.MongoSpringBoot.DTOs.OrderDto;
import com.MongoSpringBoot.OrdersApiDelegate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/orders")
public class OrdersApiDelegateImpl implements OrdersApiDelegate {



    @Override
    @GetMapping
    public ResponseEntity<OrderDto> getOrder(@RequestParam("id") String orderId) {

        return null;
    }
}
