package com.MongoSpringBoot.service.impl;

import com.MongoSpringBoot.events.ProductEvent;
import com.MongoSpringBoot.model.ProductsEntity;
import com.MongoSpringBoot.service.ProductEventService;
import com.MongoSpringBoot.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductEventServiceImpl implements ProductEventService {

    private final ProductService productService;

    @Override
    public void handleProductEvent(ProductEvent productEvent) {

        log.info("ProductEventServiceImpl handleProductEvent (productEvent: {})", productEvent);

        String name = productEvent.getName();
        String description = productEvent.getDescription();

        Optional<ProductsEntity> productOpt = productService.getProductByNameAndDescription(name, description);
        if (productOpt.isPresent()) {
            log.info("Product already present");
            return;
        }

        ProductsEntity productFromEvent = productService.toProductFromEvent(productEvent);
        productService.saveProduct(productFromEvent);

        log.info("ProductEventServiceImpl handleProductEvent event processed");
    }
}
