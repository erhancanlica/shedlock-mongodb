package com.aaroen.shedlock.schedule;

import com.aaroen.shedlock.persistence.entity.KafkaMessage;
import com.aaroen.shedlock.persistence.entity.Shedlock;
import com.aaroen.shedlock.persistence.service.KafkaMessageService;
import com.aaroen.shedlock.persistence.service.MessageService;
import com.aaroen.shedlock.persistence.service.ShedlockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageScheduler {

    private static final String LOCK_NAME = "customerMessage";

    private final MessageService messageService;
    private final ShedlockService shedlockService;
    private final KafkaMessageService kafkaMessageService;

    @SchedulerLock(name = LOCK_NAME)
    @Scheduled(fixedDelayString = "5000")
    public void syncCustomerMessage() throws InterruptedException {
        Shedlock lock = shedlockService.getLock(LOCK_NAME);
        log.info("method locked from {} instance until {}", lock.getLockedBy(), lock.getLockedUntil());

        List<KafkaMessage> kafkaMessages = kafkaMessageService.getAllByCommitAndStartOfDay();
        if (!CollectionUtils.isEmpty(kafkaMessages)) {
            kafkaMessages.forEach(message -> {
                log.info("processing kafka message with id: {}", message);
                messageService.insert(message);
            });
        }

        Thread.sleep(20000);
        log.info("all messages have been processed, removing lock for instance");
        shedlockService.unlock(LOCK_NAME);
    }
}