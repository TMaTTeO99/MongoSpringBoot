package com.MongoSpringBoot.service.impl;

import com.MongoSpringBoot.DTOs.OrderDto;
import com.MongoSpringBoot.DTOs.ProductDto;
import com.MongoSpringBoot.enums.OrderStatus;
import com.MongoSpringBoot.exception.EntityNotFoundException;
import com.MongoSpringBoot.model.OrderItem;
import com.MongoSpringBoot.model.OrdersEntity;
import com.MongoSpringBoot.repository.OrdersRepository;
import com.MongoSpringBoot.service.OrdersService;
import com.MongoSpringBoot.service.UsersService;
import com.MongoSpringBoot.service.mapper.OrdersMapper;
import com.MongoSpringBoot.service.mapper.OrdersMapperImpl;
import com.MongoSpringBoot.service.mapper.ProductMapper;
import com.MongoSpringBoot.service.mapper.ProductMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import scala.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrdersServiceImplTest {


    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private UsersService usersService;

    @Mock
    private OutletServiceImpl outletService;

    private ProductMapper productMapper;
    private OrdersMapper ordersMapper;
    private OrdersService ordersService;

    @BeforeEach
    void setUp() {

        productMapper = new ProductMapperImpl();
        ordersMapper = new OrdersMapperImpl(productMapper);
        ordersService = new OrdersServiceImpl(ordersRepository, ordersMapper, usersService, outletService);
    }

    @Test
    void getOrderById_ShouldGetOrderById(){

        Long id = 1L;
        Long userId = 1L;

        OrdersEntity ordersEntity = buildMockOrdersEntity(id, userId);
        OrderDto orderDto = buildMockOrderDto(id, userId);

        LocalDateTime currentLocalDate = LocalDateTime.of(2025, 10,15, 10, 0, 0);

        try (MockedStatic<LocalDateTime> topDateTimeUtilMock = Mockito.mockStatic(LocalDateTime.class)) {
            topDateTimeUtilMock.when(LocalDateTime::now).thenReturn(currentLocalDate);

            when(ordersRepository.findById(id)).thenReturn(Optional.of(ordersEntity));
            OrderDto orderById = ordersService.getOrderById(id);
            assertEquals(orderDto, orderById);

        }

    }

    @Test
    void getOrderById_ShouldFailure_WrongId() {

        Long id = 1L;
        LocalDateTime currentLocalDate = LocalDateTime.of(2025, 10,15, 10, 0, 0);

        try (MockedStatic<LocalDateTime> topDateTimeUtilMock = Mockito.mockStatic(LocalDateTime.class)) {
            topDateTimeUtilMock.when(LocalDateTime::now).thenReturn(currentLocalDate);

            when(ordersRepository.findById(id)).thenReturn(Optional.empty());
            assertThrows(EntityNotFoundException.class, () -> ordersService.getOrderById(id));
        }
    }

    @Test
    void addOrder_ShouldAddOrder_Success() {

        Long userId = 1L;
        Long outletId = 2L;
        Long generatedOrderId = 100L;
        LocalDateTime fixedTime = LocalDateTime.of(2025, 10, 15, 10, 0, 0);

        OrderDto inputDto = buildMockOrderDto(null, null);

        OrdersEntity entityToSave = ordersMapper.toEntityFromDto(inputDto);
        entityToSave.setUserId(userId);
        entityToSave.setOutletId(outletId);
        entityToSave.setOrderDate(fixedTime);
        entityToSave.setOrderStatus(OrderStatus.Accepted); 

        OrdersEntity savedEntity = buildMockOrdersEntity(generatedOrderId, userId);
        savedEntity.setOutletId(outletId);
        savedEntity.setOrderDate(fixedTime);
        savedEntity.setOrderStatus(OrderStatus.Accepted);

        try (MockedStatic<LocalDateTime> topDateTimeUtilMock = Mockito.mockStatic(LocalDateTime.class)) {
            topDateTimeUtilMock.when(LocalDateTime::now).thenReturn(fixedTime);

            Mockito.lenient().when(usersService.internalGetUserById(userId)).thenReturn(null);
            Mockito.lenient().when(outletService.getOutletById(outletId)).thenReturn(null);

            when(ordersRepository.save(Mockito.any(OrdersEntity.class))).thenReturn(savedEntity);

            OrderDto result = ordersService.addOrder(userId, outletId, inputDto);

            assertEquals(generatedOrderId, result.getOrderId());
            assertEquals(userId, result.getUserId());
            assertEquals(OrderStatus.Accepted, result.getOrderStatus());
            assertEquals(fixedTime, result.getOrderDate());

            assertEquals(inputDto.getItems().size(), result.getItems().size());
            assertEquals(inputDto.getItems().get(0).getName(), result.getItems().get(0).getName());

            Mockito.verify(usersService).internalGetUserById(userId);
            Mockito.verify(outletService).getOutletById(outletId);
            Mockito.verify(ordersRepository).save(Mockito.any(OrdersEntity.class));
        }
    }

    @Test
    void addOrder_ShouldThrowEntityNotFound_WhenUserDoesNotExist() {

        Long userId = 99L;
        Long outletId = 2L;
        OrderDto inputDto = new OrderDto();

        Mockito.doThrow(new EntityNotFoundException("User not found"))
                .when(usersService).internalGetUserById(userId);

        assertThrows(EntityNotFoundException.class,
                () -> ordersService.addOrder(userId, outletId, inputDto));

        Mockito.verify(ordersRepository, Mockito.never()).save(Mockito.any());
        Mockito.verify(outletService, Mockito.never()).getOutletById(Mockito.anyLong());
    }

    @Test
    void addOrder_ShouldThrowEntityNotFound_WhenOutletDoesNotExist() {

        Long userId = 1L;
        Long outletId = 99L;
        OrderDto inputDto = new OrderDto();

        Mockito.lenient().when(usersService.internalGetUserById(userId)).thenReturn(null);

        Mockito.doThrow(new EntityNotFoundException("Outlet not found"))
                .when(outletService).getOutletById(outletId);

        assertThrows(EntityNotFoundException.class,
                () -> ordersService.addOrder(userId, outletId, inputDto));

        Mockito.verify(ordersRepository, Mockito.never()).save(Mockito.any());
    }

    private OrdersEntity buildMockOrdersEntity(Long id, Long UserId) {

        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setId(id);
        ordersEntity.setOrderStatus(OrderStatus.Accepted);
        ordersEntity.setUserId(UserId);
        ordersEntity.setOrderDate(LocalDateTime.of(2025, 10,15, 10, 0, 0));


        OrderItem item_1 = new OrderItem();
        item_1.setName("Scarpe");
        item_1.setPrice(49.99);
        item_1.setQuantity(1);
        List<OrderItem> items = List.of(
                item_1
        );
        ordersEntity.setItems(items);
        return ordersEntity;
    }

    private OrderDto buildMockOrderDto(Long id, Long UserId) {

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(id);
        orderDto.setOrderDate(LocalDateTime.of(2025, 10,15, 10, 0, 0));
        orderDto.setOrderStatus(OrderStatus.Accepted);
        orderDto.setUserId(UserId);

        ProductDto productDto = new ProductDto();
        productDto.setName("Scarpe");
        productDto.setPrice(49.99);
        productDto.setQuantity(1);
        List<ProductDto> productsDto = List.of(productDto);

        orderDto.setItems(productsDto);
        return orderDto;
    }
}
