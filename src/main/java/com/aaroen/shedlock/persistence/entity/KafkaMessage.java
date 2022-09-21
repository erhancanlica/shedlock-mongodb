package com.aaroen.shedlock.persistence.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "kafka_message")
public class KafkaMessage {
    @Id
    private String id;
    private String message;
    private Long atCreated;
    private Boolean commit;
}