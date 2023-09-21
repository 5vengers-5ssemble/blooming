package com.fivengers.blooming.artist.application.port.in;

import com.fivengers.blooming.artist.application.port.in.dto.ArtistCreateRequest;
import com.fivengers.blooming.artist.domain.Artist;
import java.util.List;

public interface ArtistUseCase {

    Artist add(ArtistCreateRequest request, Long memberId);
    List<Artist> searchAll();
    Artist searchById(Long artistId);

}
