package com.fivengers.blooming.live.application.port.out;

import com.fivengers.blooming.live.domain.Live;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LivePort {

    Page<Live> findActiveLive(Pageable pageable);
    Page<Live> findByKeyword(String keyword, Pageable pageable);
    Page<Live> findByArtistStageName(String keyword, Pageable pageable);

    boolean isNonExistentLive(Long liveId);
    Live save(Live live);

    int findLiveCountByWeek(Long artistId, LocalDate endOfWeek);
}
