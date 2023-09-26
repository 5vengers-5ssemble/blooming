package com.fivengers.blooming.artist.application.port.in;

import com.fivengers.blooming.artist.domain.ArtistVideo;
import java.util.List;

public interface ArtistVideoUseCase {

    List<ArtistVideo> searchByArtistId(Long artistId);
}
