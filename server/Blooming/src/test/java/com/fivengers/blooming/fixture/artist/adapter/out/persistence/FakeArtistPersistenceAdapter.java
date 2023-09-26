package com.fivengers.blooming.fixture.artist.adapter.out.persistence;

import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.artist.domain.Artist;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeArtistPersistenceAdapter implements ArtistPort {

    private final Map<Long, Artist> store = new HashMap<>();
    private Long autoIncrementId = 1L;

    @Override
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

    @Override
    public Optional<Artist> findById(Long artistId) {
        return Optional.ofNullable(store.get(artistId));
    }

    @Override
    public Artist update(Artist artist) {
        Artist persistedArtist = store.get(artist.getId());
        store.put(artist.getId(), Artist.builder()
                .id(artist.getId())
                .stageName(artist.getStageName())
                .agency(artist.getAgency())
                .description(artist.getDescription())
                .profileImageUrl(artist.getProfileImageUrl())
                .youtubeUrl(artist.getYoutubeUrl())
                .fanCafeUrl(artist.getFanCafeUrl())
                .snsUrl(artist.getSnsUrl())
                .createdAt(persistedArtist.getCreatedAt())
                .modifiedAt(LocalDateTime.now())
                .build());
        return artist;
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
                .profileImageUrl(artist.getProfileImageUrl())
                .youtubeUrl(artist.getYoutubeUrl())
                .fanCafeUrl(artist.getFanCafeUrl())
                .snsUrl(artist.getSnsUrl())
                .createdAt(now)
                .modifiedAt(now)
                .build();
        store.put(autoIncrementId, persistedArtist);
        autoIncrementId++;
        return persistedArtist;
    }
}
