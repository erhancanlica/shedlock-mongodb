package com.aaroen.shedlock.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.mongo.MongoLockProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.aaroen.shedlock.persistence.entity", "com.aaroen.shedlock.persistence.repository"})
public class MongoConfiguration extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Bean
    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(this.uri);
    }

    @Override
    protected String getDatabaseName() {
        return this.database;
    }

    @Bean
    public LockProvider lockProvider(MongoClient mongoClient) {
        return new MongoLockProvider(mongoClient.getDatabase(getDatabaseName()).getCollection("shedlock"));
    }
}