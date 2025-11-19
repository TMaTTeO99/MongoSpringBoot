package com.MongoSpringBoot.policy;

import com.MongoSpringBoot.enums.MyAppEventType;
import com.MongoSpringBoot.events.MessageEvent;
import com.MongoSpringBoot.service.MessageEventService;
import com.MongoSpringBoot.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MessageEventHandler implements EventHandler<MessageEvent> {

    private final MessageEventService messageService;

    @Override
    public boolean supports(String eventType) {
        return StringUtils.equalsIgnoreCase(
                eventType, MyAppEventType.MESSAGE_EVENT.name());
    }

    @Override
    public MessageEvent transform(String payload) {
        return JsonUtils.fromJSon(payload, MessageEvent.class);
    }

    @Override
    public void handle(MessageEvent event) {
        messageService.saveMessage(event);
    }
}
