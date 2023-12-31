package com.fivengers.blooming.live.application.port.in;

import com.fivengers.blooming.live.adapter.in.web.dto.LiveFrequencyDetailsRequest;
import com.fivengers.blooming.live.domain.Live;
import com.fivengers.blooming.live.domain.LiveFrequency;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LiveSearchUseCase {

    Live searchActiveLiveById(Long liveId);
    Page<Live> searchActiveLive(Pageable pageable);
    Page<Live> searchByKeyword(String query, Pageable pageable);

    Page<Live> searchByArtist(String query, Pageable pageable);
    List<LiveFrequency> searchLiveFrequencyByArtist(
            LiveFrequencyDetailsRequest liveFrequencyDetailsRequest);

    Long checkActiveLive(Long artistId);

    List<Live> searchBestLive(int numberOfLives);
    List<Live> searchLiveByNftPurchasedArtist(Long memberId);

}
