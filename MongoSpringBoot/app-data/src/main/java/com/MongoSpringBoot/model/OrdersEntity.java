package com.MongoSpringBoot.model;

import com.MongoSpringBoot.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document("Orders")
public class OrdersEntity {

    public static final String SEQUENCE_NAME = "orders_sequence";

    @Id
    private Long id;

    @Indexed
    private Long userId;

    @Indexed
    private Long outletId;

    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private List<OrderItem> items;

}
