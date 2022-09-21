package com.aaroen.shedlock.consumer;

import com.aaroen.shedlock.persistence.entity.KafkaMessage;
import com.aaroen.shedlock.persistence.repository.KafkaMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageConsumer {

    private final KafkaMessageRepository messageRepository;

    @KafkaListener(topics = "${kafka-consumer.kafka.topic}")
    public void consumeMessageToKafka(String consumerRecord, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info("Received message: {}, with partition: {}, thread: {}", consumerRecord, partition, Thread.currentThread().getName());
        KafkaMessage message = KafkaMessage.builder()
                .id(UUID.randomUUID().toString())
                .message(consumerRecord)
                .atCreated(System.currentTimeMillis())
                .commit(false)
                .build();
        messageRepository.save(message);
        log.info("Completed message: {}, with partition: {}, thread: {}", consumerRecord, partition, Thread.currentThread().getName());
    }
}