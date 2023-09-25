package com.fivengers.blooming.artistscrap.application.port.out;

import com.fivengers.blooming.artistscrap.domain.ArtistScrap;

public interface ArtistScrapPort {

    void saveScrap(ArtistScrap artistScrap);

    void deleteScrap(Long memberId, Long artistId);

    ArtistScrap findByMemberIdAndArtistId(Long memberId, Long artistId);
}
