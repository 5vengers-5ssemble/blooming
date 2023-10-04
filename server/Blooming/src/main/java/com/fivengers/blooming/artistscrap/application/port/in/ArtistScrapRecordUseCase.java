package com.fivengers.blooming.artistscrap.application.port.in;

import com.fivengers.blooming.artistscrap.domain.ArtistScrapRecord;
import java.util.List;

public interface ArtistScrapRecordUseCase {

    List<ArtistScrapRecord> findOnLatestFourWeek(Long artistId);
}
