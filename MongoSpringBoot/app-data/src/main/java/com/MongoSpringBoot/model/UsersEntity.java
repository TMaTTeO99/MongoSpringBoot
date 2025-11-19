package com.MongoSpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document("Users")
public class UsersEntity {

    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private List<MessagesEntity> messages;

}
