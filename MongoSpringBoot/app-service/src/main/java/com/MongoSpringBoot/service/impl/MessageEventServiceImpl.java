package com.MongoSpringBoot.service.impl;

import com.MongoSpringBoot.events.MessageEvent;
import com.MongoSpringBoot.model.MessagesEntity;
import com.MongoSpringBoot.model.UsersEntity;
import com.MongoSpringBoot.service.MessageEventService;
import com.MongoSpringBoot.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageEventServiceImpl implements MessageEventService {

    private final UsersService usersService;

    @Override
    public void saveMessage(MessageEvent event) {

        log.info("Service MessageEventService saveMessage: (event: {})", event);

        UsersEntity user = usersService.internalGetUserById(event.getId());

        if (user.getMessages() == null) {
            user.setMessages(new ArrayList<>());
        }
        user.getMessages().add(MessagesEntity
                .builder()
                .message(event.getMessage())
                .id(event.getId())
                .build());

        usersService.saveUser(user);

        log.info("MessageEventServiceImpl saveMessage event processed");

    }
}
