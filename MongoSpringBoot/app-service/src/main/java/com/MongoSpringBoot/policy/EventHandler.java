package com.MongoSpringBoot.policy;

import lombok.SneakyThrows;

public interface EventHandler<T> {

    boolean supports(String eventType);

    T transform(String payload);

    @SneakyThrows
    void handle(T event, String key);
}