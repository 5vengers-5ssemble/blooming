package com.fivengers.blooming.artistscrap.application.port.out;

import com.fivengers.blooming.artistscrap.domain.ArtistScrap;
import java.util.Optional;

public interface ArtistScrapPort {

    boolean scraped(Long artistId, Long memberId);
    void saveScrap(ArtistScrap artistScrap);
    void deleteScrap(Long memberId, Long artistId);
    Optional<ArtistScrap> findByMemberIdAndArtistId(Long memberId, Long artistId);
}
