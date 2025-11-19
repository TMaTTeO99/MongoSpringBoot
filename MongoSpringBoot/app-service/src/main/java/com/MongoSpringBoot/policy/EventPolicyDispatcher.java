package com.MongoSpringBoot.policy;

import com.MongoSpringBoot.AppConstants;
import com.MongoSpringBoot.utils.HeaderUtils;
import com.MongoSpringBoot.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventPolicyDispatcher {

    private final List<EventHandler<?>> eventHandlers;

    public void dispatchToHandlers(Message<?> message) {

        String kafkaEventTypeHeaderValue =
                HeaderUtils.convertHeader(
                        message.getHeaders().get(AppConstants.KAFKA_HEADER_EVENT_TYPE));

        String kafkaKey =
                HeaderUtils.convertHeader(message.getHeaders().get(KafkaHeaders.RECEIVED_KEY));

        String messagePayload = JsonUtils.convertPayload(message.getPayload());
        long start = System.currentTimeMillis();

        try {
            eventHandlers.stream()
                    .filter(handler -> handler.supports(kafkaEventTypeHeaderValue))
                    .forEach(handler -> processEvent(handler, messagePayload, kafkaKey));
            log.debug(
                    "Handled Message of type {} in {} millis",
                    kafkaEventTypeHeaderValue,
                    System.currentTimeMillis() - start);
        } catch (Exception e) {
            log.error("Error handling message of type {}", kafkaEventTypeHeaderValue, e);
            throw e;
        }
    }

    private <T> void processEvent(EventHandler<T> handler, String messagePayload, String kafkaKey) {
        var event = handler.transform(messagePayload);
        handler.handle(event, kafkaKey);
    }
}
