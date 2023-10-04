package com.fivengers.blooming.artist.application.port.out;

import com.fivengers.blooming.artist.domain.ArtistVideo;
import java.util.List;

public interface ArtistVideoPort {

    List<ArtistVideo> findByArtistId(Long artistId);

    ArtistVideo save(ArtistVideo artistVideo);
}
