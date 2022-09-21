package com.aaroen.shedlock.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "shedlock")
public class Shedlock {
    @Id
    private String name;
    private Date lockedAt;
    private Date lockedUntil;
    private String lockedBy;
}