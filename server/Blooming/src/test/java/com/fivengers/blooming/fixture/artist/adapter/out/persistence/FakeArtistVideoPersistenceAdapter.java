package com.fivengers.blooming.fixture.artist.adapter.out.persistence;

import com.fivengers.blooming.artist.application.port.out.ArtistVideoPort;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artist.domain.ArtistVideo;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeArtistVideoPersistenceAdapter implements ArtistVideoPort {

    private final Map<Long, ArtistVideo> store = new HashMap<>();
    private Long autoIncrementId = 1L;

    @Override
    public ArtistVideo save(ArtistVideo artistVideo) {
        if (isPersistenceObject(artistVideo)) {
            store.put(artistVideo.getId(), artistVideo);
            return artistVideo;
        }
        return persist(artistVideo);
    }

    @Override
    public ArtistVideo update(ArtistVideo artistVideo) {
        ArtistVideo prev = store.get(artistVideo.getId());
        ArtistVideo modified = ArtistVideo.builder()
                .id(prev.getId())
                .videoUrl(artistVideo.getVideoUrl())
                .createdAt(prev.getCreatedAt())
                .modifiedAt(LocalDateTime.now())
                .artist(prev.getArtist())
                .build();
        store.put(prev.getId(), modified);
        return modified;
    }

    @Override
    public void deleteById(Long artistVideoId) {
        store.remove(artistVideoId);
    }

    @Override
    public Optional<ArtistVideo> findById(Long artistVideoId) {
        return Optional.ofNullable(store.get(artistVideoId));
    }

    @Override
    public List<ArtistVideo> findByArtistId(Long artistId) {
        return store.values().stream()
                .filter(artistVideo -> artistVideo.getArtist().getId().equals(artistId))
                .toList();
    }

    private static boolean isPersistenceObject(ArtistVideo artistVideo) {
        return artistVideo.getId() != null;
    }

    private ArtistVideo persist(ArtistVideo artistVideo) {
        LocalDateTime now = LocalDateTime.now();
        ArtistVideo persistedArtistVideo = ArtistVideo.builder()
                .id(autoIncrementId)
                .videoUrl(artistVideo.getVideoUrl())
                .createdAt(now)
                .modifiedAt(now)
                .build();

        store.put(autoIncrementId, persistedArtistVideo);
        autoIncrementId++;
        return persistedArtistVideo;
    }
}
