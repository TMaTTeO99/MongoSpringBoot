package com.MongoSpringBoot.policy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class EventPolicy {

    @Bean
    public Consumer<Message<?>> messageConsumer() {

        return (Message<?> message) -> {
            log.info("Message received: " + message);
            try {

            } catch (Exception e) {
                log.error("Cannot process message from provisionRequest topic", e);
                throw e;
            }
        };

    }

}
