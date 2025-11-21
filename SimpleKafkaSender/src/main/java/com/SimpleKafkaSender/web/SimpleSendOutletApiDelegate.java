package com.SimpleKafkaSender.web;

import com.SimpleKafkaSender.DTOs.OutletDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/outlet")
public class SimpleSendOutletApiDelegate {

    private final StreamBridge streamBridge;

    @PostMapping
    public String sendOutlet(@RequestParam("event") String eventType, @RequestBody OutletDto outletDto) {

        Message<OutletDto> kafkaMessage = MessageBuilder
                .withPayload(outletDto)
                .setHeader("x-event-type", eventType)
                .build();

        if (streamBridge.send("message-output", kafkaMessage)) {
            return "Message sent successfully with type: " + eventType;
        } else {
            return "Failed to send message.";
        }

    }

}
