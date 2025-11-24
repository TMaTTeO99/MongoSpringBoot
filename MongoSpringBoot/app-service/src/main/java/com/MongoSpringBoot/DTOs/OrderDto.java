package com.MongoSpringBoot.DTOs;

import com.MongoSpringBoot.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private Long orderId;
    private LocalDateTime orderDate;
    private Long userId;
    private Long outletId;
    private OrderStatus orderStatus;
    private List<ProductDto> items;

}
