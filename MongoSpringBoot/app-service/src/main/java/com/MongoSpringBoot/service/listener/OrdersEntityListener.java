package com.MongoSpringBoot.service.listener;


import com.MongoSpringBoot.model.OrdersEntity;
import com.MongoSpringBoot.model.OutletEntity;
import com.MongoSpringBoot.service.impl.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class OrdersEntityListener extends AbstractMongoEventListener<OrdersEntity> {

    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<OrdersEntity> event) {

        OrdersEntity source = event.getSource();
        if (source.getId() == null || source.getId() < 1) {
            source.setId(sequenceGenerator.generateSequence(OrdersEntity.SEQUENCE_NAME));
        }
    }
}
