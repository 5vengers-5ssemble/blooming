package com.fivengers.blooming.artist.application.port.in;

import com.fivengers.blooming.artist.application.port.in.dto.ArtistCreateRequest;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistModifyRequest;
import com.fivengers.blooming.artist.domain.Artist;
import java.util.List;

public interface ArtistUseCase {

    Artist add(ArtistCreateRequest request);
    List<Artist> searchAll();
    Artist searchById(Long artistId);
    Artist searchByMemberId(Long memberId);
    Artist modify(ArtistModifyRequest request, Long artistId, Long memberId);

}
