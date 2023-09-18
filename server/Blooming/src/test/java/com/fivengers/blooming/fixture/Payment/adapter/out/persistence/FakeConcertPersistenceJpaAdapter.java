package com.fivengers.blooming.fixture.Payment.adapter.out.persistence;

import com.fivengers.blooming.Concert.domain.Concert;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class FakeConcertPersistenceJpaAdapter {

    private final Map<Long, Concert> store = new HashMap<>();
    private Long autoIncrementId = 1L;

    public Concert save(Concert concert) {
        if (isPersistenceObject(concert)) {
            store.put(concert.getId(), concert);
            return concert;
        }
        return persist(concert);
    }

    private boolean isPersistenceObject(Concert concert) {
        return concert.getId() != null;
    }

    private Concert persist(Concert concert) {
        LocalDateTime now = LocalDateTime.now();
        Concert persistedConcert = Concert.builder()
                .id(autoIncrementId)
                .name(concert.getName())
                .build();
        store.put(autoIncrementId, persistedConcert);
        autoIncrementId++;
        return persistedConcert;
    }
}
