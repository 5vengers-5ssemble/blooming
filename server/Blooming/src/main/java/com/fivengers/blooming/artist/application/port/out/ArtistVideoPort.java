package com.fivengers.blooming.artist.application.port.out;

import com.fivengers.blooming.artist.domain.ArtistVideo;
import java.util.List;
import java.util.Optional;

public interface ArtistVideoPort {

    Optional<ArtistVideo> findById(Long artistVideoId);
    List<ArtistVideo> findByArtistId(Long artistId);
    ArtistVideo save(ArtistVideo artistVideo);
    ArtistVideo update(ArtistVideo artistVideo);
    void deleteById(Long artistVideoId);
}
