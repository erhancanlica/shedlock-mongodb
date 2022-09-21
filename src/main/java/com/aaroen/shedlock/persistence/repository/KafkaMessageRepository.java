package com.aaroen.shedlock.persistence.repository;

import com.aaroen.shedlock.persistence.entity.KafkaMessage;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface KafkaMessageRepository extends MongoRepository<KafkaMessage, String> {

    @Query(value = "{$and: [{'commit': ?0}, {'atCreated': {'$gte': ?1}}]}")
    List<KafkaMessage> findAllByCommitAndAndAtCreated(boolean commit, long startOfDay);
}