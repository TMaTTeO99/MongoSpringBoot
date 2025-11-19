package com.MongoSpringBoot.service;

import com.MongoSpringBoot.events.MessageEvent;

public interface MessageService {

    void printMessage(MessageEvent event);
}
