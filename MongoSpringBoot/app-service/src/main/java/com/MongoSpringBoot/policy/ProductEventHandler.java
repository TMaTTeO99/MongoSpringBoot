package com.MongoSpringBoot.policy;

import com.MongoSpringBoot.enums.MyAppEventType;
import com.MongoSpringBoot.events.ProductEvent;
import com.MongoSpringBoot.service.ProductEventService;
import com.MongoSpringBoot.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEventHandler implements EventHandler<ProductEvent> {

    private final ProductEventService productEventService;

    @Override
    public boolean supports(String eventType) {
        return StringUtils.equals(eventType, MyAppEventType.PRODUCT_EVENT.name());
    }

    @Override
    public ProductEvent transform(String payload) {
        return JsonUtils.fromJSon(payload, ProductEvent.class);
    }

    @Override
    public void handle(ProductEvent event) {
        productEventService.handleProductEvent(event);
    }
}
