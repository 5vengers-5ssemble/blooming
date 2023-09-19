package com.fivengers.blooming.fixture.artist.adapter.out.persistence;

import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.artist.domain.Artist;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeArtistPersistenceAdapter implements ArtistPort {

    private final Map<Long, Artist> store = new HashMap<>();
    private Long autoIncrementId = 1L;

    public Artist save(Artist artist) {
        if (isPersistenceObject(artist)) {
            store.put(artist.getId(), artist);
            return artist;
        }
        return persist(artist);
    }

    @Override
    public List<Artist> findAll() {
        return store.values().stream()
                .toList();
    }

    private static boolean isPersistenceObject(Artist artist) {
        return artist.getId() != null;
    }

    private Artist persist(Artist artist) {
        LocalDateTime now = LocalDateTime.now();
        Artist persistedArtist = Artist.builder()
                .id(autoIncrementId)
                .stageName(artist.getStageName())
                .agency(artist.getAgency())
                .description(artist.getDescription())
                .createdAt(now)
                .modifiedAt(now)
                .build();
        store.put(autoIncrementId, persistedArtist);
        autoIncrementId++;
        return persistedArtist;
    }
}