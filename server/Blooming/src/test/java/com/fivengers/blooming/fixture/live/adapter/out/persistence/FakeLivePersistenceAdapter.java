package com.fivengers.blooming.fixture.live.adapter.out.persistence;

import com.fivengers.blooming.live.application.port.out.LivePort;
import com.fivengers.blooming.live.domain.Live;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;

public class FakeLivePersistenceAdapter implements LivePort {

    private final Map<Long, Live> store = new HashMap<>();
    private Long autoIncrementId = 1L;

    public Live save(Live live) {
        if (isPersistenceObject(live)) {
            store.put(live.getId(), live);
            return live;
        }
        return persist(live);
    }

    @Override
    public List<Live> findByKeyword(String keyword, Pageable pageable) {
        return store.values().stream()
                .filter(live -> live.getTitle().contains(keyword))
                .toList();
    }

    private static boolean isPersistenceObject(Live live) {
        return live.getId() != null;
    }

    private Live persist(Live live) {
        LocalDateTime now = LocalDateTime.now();
        Live persistedLive = Live.builder()
                .id(autoIncrementId)
                .artist(live.getArtist())
                .title("모아모아띄워띄워펀펀")
                .createdAt(now)
                .modifiedAt(now)
                .build();
        store.put(autoIncrementId, persistedLive);
        autoIncrementId++;
        return persistedLive;
    }
}
