package com.aaroen.shedlock.persistence.service;

import com.aaroen.shedlock.persistence.entity.KafkaMessage;
import com.aaroen.shedlock.persistence.entity.Message;
import com.aaroen.shedlock.persistence.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public void insert(KafkaMessage kafkaMessage) {
        if (Objects.nonNull(kafkaMessage)) {
            Message message = Message.builder()
                    .id(UUID.randomUUID().toString())
                    .message(kafkaMessage.getMessage())
                    .atCreated(new Date(kafkaMessage.getAtCreated()))
                    .build();
            messageRepository.save(message);
        } else {
            throw new RuntimeException();
        }
    }
}