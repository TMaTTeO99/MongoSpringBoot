package com.MongoSpringBoot.policy;

import com.MongoSpringBoot.enums.MyAppEventType;
import com.MongoSpringBoot.events.OutletEvent;
import com.MongoSpringBoot.service.OutletEventService;
import com.MongoSpringBoot.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutletEventHandler implements EventHandler<OutletEvent> {

    private final OutletEventService outletEventService;

    @Override
    public boolean supports(String eventType) {
        return StringUtils.equals(eventType, MyAppEventType.OUTLET_EVENT.name());
    }

    @Override
    public OutletEvent transform(String payload) {
        return JsonUtils.fromJSon(payload, OutletEvent.class);
    }

    @Override
    public void handle(OutletEvent event) {
        outletEventService.handleOutletEvent(event);
    }
}
