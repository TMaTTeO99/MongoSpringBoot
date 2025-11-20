package com.MongoSpringBoot.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("Outlet")
public class OutletEntity {

    public static final String SEQUENCE_NAME = "outlets_sequence";

    @Id
    private Long id;
    private String name;
    private String description;
    private String address;

}
