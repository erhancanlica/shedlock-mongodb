package com.aaroen.shedlock.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("kafka-consumer.kafka")
public class KafkaProperties {
    private Integer concurrency;
    private String maxPoolRecord;
    private String consumerGroupId;
    private String bootstrapServers;
    private String autoCommitInterval;
}