package com.MongoSpringBoot.config.dbmigrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

@ChangeUnit(id = "001-creazione-indici-orders", order = "001", author = "MatteoT", transactional = false)

public class OrdersIndexMigration {

    private final MongoTemplate mongoTemplate;

    public OrdersIndexMigration(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Execution
    public void execute() {

        mongoTemplate.indexOps("Orders")
                .createIndex(new Index().on("userId", Sort.Direction.ASC)
                        .named("idx_user_id"));
        mongoTemplate.indexOps("Orders")
                .createIndex(new Index().on("orderId", Sort.Direction.ASC)
                        .named("idx_order_id"));

    }

    @RollbackExecution
    public void rollback() {

        mongoTemplate.indexOps("Orders").dropIndex("idx_user_id");
        mongoTemplate.indexOps("Orders").dropIndex("idx_order_id");

    }
}
