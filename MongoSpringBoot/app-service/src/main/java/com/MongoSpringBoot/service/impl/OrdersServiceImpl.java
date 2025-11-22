package com.MongoSpringBoot.service.impl;

import com.MongoSpringBoot.DTOs.OrderDto;
import com.MongoSpringBoot.enums.OrderStatus;
import com.MongoSpringBoot.exception.EntityNotFoundException;
import com.MongoSpringBoot.model.OrdersEntity;
import com.MongoSpringBoot.model.UsersEntity;
import com.MongoSpringBoot.repository.OrdersRepository;
import com.MongoSpringBoot.service.OrdersService;
import com.MongoSpringBoot.service.UsersService;
import com.MongoSpringBoot.service.mapper.OrdersMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper;
    private final UsersService usersService;
    private final OutletServiceImpl outletService;

    @Override
    public OrderDto getOrderById(Long id) {

        if (log.isDebugEnabled()) {
            log.debug("OrdersService getOrderById: (id: {})", id);
        }

        OrdersEntity orderOpt =
                ordersRepository
                        .findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Order not found: " + id));

        return ordersMapper.toDtoFromEntity(orderOpt);
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            rollbackFor = { Exception.class }
    )
    public OrderDto addOrder(Long userId, Long outletId, OrderDto order) {

        if (log.isDebugEnabled()) {
            log.debug("OrdersApiDelegate addOrder: (order: {})", order);
        }

        usersService.internalGetUserById(userId);
        outletService.getOutletById(outletId);

        OrdersEntity orderEntity = ordersMapper.toEntityFromDto(order);

        orderEntity.setUserId(userId);
        orderEntity.setOutletId(outletId);

        // Here we simulate money transfer
        if (moneyTransfer()) {
            orderEntity.setOrderStatus(OrderStatus.Accepted);
        } else {
            orderEntity.setOrderStatus(OrderStatus.Accepted);
        }


        OrdersEntity orderSaved = ordersRepository.save(orderEntity);

        return ordersMapper.toDtoFromEntity(orderSaved);
    }

    private boolean moneyTransfer() {
        return true;
    }
}
