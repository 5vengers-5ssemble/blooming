package com.fivengers.blooming.fixture.Payment.adapter.out.persistence;

import com.fivengers.blooming.artist.domain.Artist;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class FakeArtistPersistenceJpaAdapter {

    private final Map<Long, Artist> store = new HashMap<>();
    private Long autoIncrementId = 1L;

    public Artist save(Artist artist) {
        if (isPersistenceObject(artist)) {
            store.put(artist.getId(), artist);
            return artist;
        }
        return persist(artist);
    }

    private boolean isPersistenceObject(Artist artist) {
        return artist.getId() != null;
    }

    private Artist persist(Artist concert) {
        LocalDateTime now = LocalDateTime.now();
        Artist persistedArtist = Artist.builder()
                .id(autoIncrementId)
                .name(concert.getName())
                .build();
        store.put(autoIncrementId, persistedArtist);
        autoIncrementId++;
        return persistedArtist;
    }
}
