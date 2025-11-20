package com.MongoSpringBoot.service.listener;

import com.MongoSpringBoot.model.ProductsEntity;
import com.MongoSpringBoot.service.impl.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class ProductsEntityListener extends AbstractMongoEventListener<ProductsEntity> {

    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<ProductsEntity> event) {

        ProductsEntity productsEntity = event.getSource();

        if (productsEntity.getId() == null || productsEntity.getId() < 1) {
            productsEntity.setId(sequenceGenerator.generateSequence(ProductsEntity.SEQUENCE_NAME));
        }

    }

}
