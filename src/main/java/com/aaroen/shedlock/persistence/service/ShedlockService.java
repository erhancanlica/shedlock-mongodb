package com.aaroen.shedlock.persistence.service;

import com.aaroen.shedlock.persistence.entity.Shedlock;
import com.aaroen.shedlock.persistence.repository.ShedlockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShedlockService {

    private final ShedlockRepository shedlockRepository;

    public Shedlock getLock(String lockName) {
        Optional<Shedlock> optionalShedlock = shedlockRepository.findById(lockName);
        return optionalShedlock.orElseThrow(RuntimeException::new);
    }

    public void unlock(String lockName) {
        Optional<Shedlock> optionalShedlock = shedlockRepository.findById(lockName);
        optionalShedlock.ifPresentOrElse(lock -> {
            lock.setLockedUntil(new Date());
            shedlockRepository.save(lock);
            log.info("shedlock record lock until date was updated for {}", lock.getName());
        }, () -> { throw new RuntimeException(); });
    }
}