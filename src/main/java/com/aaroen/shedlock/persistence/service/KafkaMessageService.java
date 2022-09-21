package com.aaroen.shedlock.persistence.service;

import com.aaroen.shedlock.persistence.entity.KafkaMessage;
import com.aaroen.shedlock.persistence.repository.KafkaMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaMessageService {

    private final KafkaMessageRepository kafkaMessageRepository;

    public List<KafkaMessage> getAllByCommitAndStartOfDay() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        long startOfDay = now.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toEpochSecond() * 1000;
        return kafkaMessageRepository.findAllByCommitAndAndAtCreated(false, startOfDay);
    }
}