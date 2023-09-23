package com.fivengers.blooming.artistscrap.application.port.in;

import com.fivengers.blooming.artistscrap.application.port.in.dto.ArtistScrapRequest;

public interface ArtistScrapUseCase {

    void scrap(ArtistScrapRequest request, Long artistId);

    void unScrap(ArtistScrapRequest request, Long artistId);
}
