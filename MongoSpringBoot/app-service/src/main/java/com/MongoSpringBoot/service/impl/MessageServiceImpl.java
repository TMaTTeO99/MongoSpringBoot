package com.MongoSpringBoot.service.impl;

import com.MongoSpringBoot.events.MessageEvent;
import com.MongoSpringBoot.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Override
    public void printMessage(MessageEvent event, String key) {

    }
}
