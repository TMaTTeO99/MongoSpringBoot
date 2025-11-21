package com.SimpleKafkaSender.web;

import com.SimpleKafkaSender.DTOs.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v1/products")
public class SimpleSendProductApiDelegate {

    private final StreamBridge streamBridge;

    @PostMapping
    public String sendProduct(@RequestParam("event") String eventType, @RequestBody ProductDto productDto) {

        Message<ProductDto> kafkaMessage = MessageBuilder
                .withPayload(productDto)
                .setHeader("x-event-type", eventType)
                .build();

        if (streamBridge.send("message-output", kafkaMessage)) {
            return "Message sent successfully with type: " + eventType;
        } else {
            return "Failed to send message.";
        }
    }

}
