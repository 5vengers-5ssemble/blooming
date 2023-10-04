package com.fivengers.blooming.artistscrap.application.port.in;

import com.fivengers.blooming.artistscrap.domain.ArtistScrap;
import java.util.List;

public interface ArtistScrapUseCase {

    boolean scraped(Long artistId, Long memberId);
    List<ArtistScrap> searchByMemberId(Long memberId);

    void scrap(Long artistId, Long memberId);

    void unScrap(Long artistId, Long memberId);
}
