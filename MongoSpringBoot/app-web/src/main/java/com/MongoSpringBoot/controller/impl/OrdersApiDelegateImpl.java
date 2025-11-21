package com.MongoSpringBoot.controller.impl;

import com.MongoSpringBoot.DTOs.OrderDto;
import com.MongoSpringBoot.OrdersApiDelegate;
import com.MongoSpringBoot.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/orders")
public class OrdersApiDelegateImpl implements OrdersApiDelegate {

    private final OrdersService ordersService;

    @Override
    @PostMapping("/{userId}/outlets/{outletId}/:add-order")
    public ResponseEntity<OrderDto> addOrder(@RequestParam("userId") Long userId, @RequestParam("outletId") Long outletId, @RequestBody OrderDto orderDto) {

        if (log.isDebugEnabled()) {
            log.debug("OrdersApiDelegate addOrder: (orderId: {})", orderDto);
        }

        return ResponseEntity.ok(ordersService.addOrder(userId, outletId, orderDto));
    }

    @Override
    @GetMapping
    public ResponseEntity<OrderDto> getOrder(@RequestParam("id") String orderId) {

        if (log.isDebugEnabled()) {
            log.debug("OrdersApiDelegate getOrder: (orderId: {})", orderId);
        }


        return null;
    }

}
