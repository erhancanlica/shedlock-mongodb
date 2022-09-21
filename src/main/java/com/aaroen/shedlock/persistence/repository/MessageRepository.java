package com.aaroen.shedlock.persistence.repository;

import com.aaroen.shedlock.persistence.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}