package com.fivengers.blooming.artistscrap.application.port.in;

import com.fivengers.blooming.artistscrap.application.port.in.dto.ArtistScrapRequest;

public interface ArtistScrapUseCase {

    boolean scraped(Long artistId, Long memberId);

    void scrap(ArtistScrapRequest request, Long artistId);

    void unScrap(ArtistScrapRequest request, Long artistId);
}
