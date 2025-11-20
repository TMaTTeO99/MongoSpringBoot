package com.MongoSpringBoot;

import com.MongoSpringBoot.DTOs.OrderDto;
import org.springframework.http.ResponseEntity;

public interface OrdersApiDelegate {

    ResponseEntity<OrderDto> getOrder(String orderId);

}
