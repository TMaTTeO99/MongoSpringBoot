package com.MongoSpringBoot.service.listener;

import com.MongoSpringBoot.model.OutletEntity;
import com.MongoSpringBoot.model.UsersEntity;
import com.MongoSpringBoot.service.impl.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutletEntityListener extends AbstractMongoEventListener<OutletEntity> {

    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<OutletEntity> event) {

        OutletEntity user = event.getSource();

        if (user.getId() == null || user.getId() < 1) {
            user.setId(sequenceGenerator.generateSequence(OutletEntity.SEQUENCE_NAME));
        }
    }
}
