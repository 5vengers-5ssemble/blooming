package com.fivengers.blooming.fixture.project.adapter.out.persistence;

import com.fivengers.blooming.project.application.port.out.ConcertPort;
import com.fivengers.blooming.project.domain.Concert;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class FakeConcertPersistenceAdapter implements ConcertPort {

    private final Map<Long, Concert> store = new HashMap<>();
    private Long autoIncrementId = 1L;

    public Concert save(Concert concert) {
        if (isPersistenceObject(concert)) {
            store.put(concert.getId(), concert);
            return concert;
        }
        return persist(concert);
    }

    private static boolean isPersistenceObject(Concert concert) {
        return concert.getId() != null;
    }

    private Concert persist(Concert concert) {
        LocalDateTime now = LocalDateTime.now();
        Concert persistedConcert = Concert.builder()
                .id(autoIncrementId)
                .name(concert.getName())
                .fundingAmount(concert.getFundingAmount())
                .startedAt(concert.getStartedAt())
                .endedAt(concert.getEndedAt())
                .description(concert.getDescription())
                .createdAt(now)
                .modifiedAt(now)
                .artist(concert.getArtist())
                .build();
        store.put(autoIncrementId, persistedConcert);
        autoIncrementId++;
        return persistedConcert;
    }

    @Override
    public List<Concert> findAll(Pageable pageable) {
        if (pageable.getSort().equals(Sort.by("fundingAmount").descending())) {
            //System.out.println("fundingAmount");
            return store.values().stream()
                    .sorted(Comparator.comparing(Concert::getFundingAmount).reversed())
                    .toList();
        } else {
            //System.out.println("createdAt");
            return store.values().stream()
                    .sorted(Comparator.comparing(Concert::getCreatedAt).reversed())
                    .toList();
        }
    }

    @Override
    public List<Concert> findAllOngoingProject(Pageable pageable) {
        if (pageable.getSort().equals(Sort.by("fundingAmount").descending())) {
            //System.out.println("fundingAmount");
            return store.values().stream()
                    .filter(concert -> concert.getEndedAt().isAfter(LocalDateTime.now()))
                    .sorted(Comparator.comparing(Concert::getFundingAmount).reversed())
                    .toList();
        } else {
            //System.out.println("createdAt");
            return store.values().stream()
                    .filter(concert -> concert.getEndedAt().isAfter(LocalDateTime.now()))
                    .sorted(Comparator.comparing(Concert::getCreatedAt).reversed())
                    .toList();
        }
    }
}
