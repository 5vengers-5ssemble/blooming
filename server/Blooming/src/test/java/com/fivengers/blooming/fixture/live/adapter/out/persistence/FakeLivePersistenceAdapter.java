package com.fivengers.blooming.fixture.live.adapter.out.persistence;

import com.fivengers.blooming.live.application.port.out.LivePort;
import com.fivengers.blooming.live.domain.Live;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    public Page<Live> findByKeyword(String keyword, Pageable pageable) {
        List<Live> list = store.values().stream()
                .filter(live -> live.getTitle().contains(keyword))
                .toList();
        return new PageImpl<>(
                list,
                pageable,
                store.size()
        );
    }

    @Override
    public Page<Live> findByArtistStageName(String keyword, Pageable pageable) {
        List<Live> list = store.values().stream()
                .filter(live -> live.getEndedAt() == null)
                .filter(live -> live.getArtist().getStageName().contains(keyword))
                .toList();
        return new PageImpl<>(list, pageable, store.size());
    }

    @Override
    public boolean isNonExistentLive(Long liveId) {
        return !store.containsKey(liveId);
    }

    private boolean isPersistenceObject(Live live) {
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
