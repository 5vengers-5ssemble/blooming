package com.fivengers.blooming.fixture.artistscrap.adapter.out.persistence;

import com.fivengers.blooming.artistscrap.application.port.out.ArtistScrapPort;
import com.fivengers.blooming.artistscrap.domain.ArtistScrap;
import com.fivengers.blooming.global.exception.artistscrap.ArtistScrapNotFoundException;
import com.fivengers.blooming.live.domain.Live;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeArtistScrapPersistenceAdapter implements ArtistScrapPort {

    private final Map<Long, ArtistScrap> store = new HashMap<>();
    private Long autoIncrementId = 1L;

    @Override
    public void saveScrap(ArtistScrap artistScrap) {
        if (isPersistenceObject(artistScrap)) {
            store.put(artistScrap.getId(), artistScrap);
            return;
        }
        persist(artistScrap);
    }

    @Override
    public void deleteScrap(Long memberId, Long artistId) {
        Optional<ArtistScrap> scrap = store.values().stream()
                .filter(artistScrap -> artistScrap.getMember().getId().equals(memberId))
                .filter(artistScrap -> artistScrap.getArtist().getId().equals(artistId))
                .findFirst();
        if (scrap.isEmpty()) {
            return;
        }
        store.remove(scrap.get().getId());
    }

    @Override
    public ArtistScrap findByMemberIdAndArtistId(Long memberId, Long artistId) {
        return store.values().stream()
                .filter(artistScrap -> artistScrap.getMember().getId().equals(memberId))
                .filter(artistScrap -> artistScrap.getArtist().getId().equals(artistId))
                .findFirst()
                .orElseThrow(ArtistScrapNotFoundException::new);
    }

    private boolean isPersistenceObject(ArtistScrap artistScrap) {
        return artistScrap.getId() != null;
    }

    private ArtistScrap persist(ArtistScrap artistScrap) {
        ArtistScrap persistArtistScrap = ArtistScrap.builder()
                .id(autoIncrementId)
                .member(artistScrap.getMember())
                .artist(artistScrap.getArtist())
                .build();
        store.put(autoIncrementId, persistArtistScrap);
        autoIncrementId++;
        return persistArtistScrap;
    }
}
