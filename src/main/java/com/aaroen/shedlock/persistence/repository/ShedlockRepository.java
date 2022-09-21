package com.aaroen.shedlock.persistence.repository;

import com.aaroen.shedlock.persistence.entity.Shedlock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShedlockRepository extends MongoRepository<Shedlock, String> {
}