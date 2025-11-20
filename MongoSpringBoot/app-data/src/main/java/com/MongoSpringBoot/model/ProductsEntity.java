package com.MongoSpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("Product")
public class ProductsEntity {

    public static final String SEQUENCE_NAME = "products_sequence";

    @Id
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;

}
