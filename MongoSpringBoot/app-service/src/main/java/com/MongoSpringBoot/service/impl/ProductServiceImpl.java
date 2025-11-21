package com.MongoSpringBoot.service.impl;

import com.MongoSpringBoot.events.ProductEvent;
import com.MongoSpringBoot.model.ProductsEntity;
import com.MongoSpringBoot.repository.ProductsRepository;
import com.MongoSpringBoot.service.ProductService;
import com.MongoSpringBoot.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;
    private final ProductMapper productMapper;


    @Override
    public Optional<ProductsEntity> getProductByNameAndDescription(String name, String description) {
        return productsRepository.findByNameAndDescription(name, description);
    }

    @Override
    public ProductsEntity saveProduct(ProductsEntity product) {
        return productsRepository.save(product);
    }

    @Override
    public ProductsEntity toProductFromEvent(ProductEvent event) {
        return productMapper.toProductEntityFromEvent(event);
    }
}
