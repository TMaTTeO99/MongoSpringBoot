package com.MongoSpringBoot.service;

import com.MongoSpringBoot.events.MessageEvent;

public interface MessageEventService {

    void saveMessage(MessageEvent event);
}
