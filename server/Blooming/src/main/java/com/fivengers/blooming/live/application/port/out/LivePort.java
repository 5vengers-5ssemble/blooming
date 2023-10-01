package com.fivengers.blooming.live.application.port.out;

import com.fivengers.blooming.live.domain.Live;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LivePort {

    Page<Live> findActiveLive(Pageable pageable);
    Optional<Live> findActiveLiveById(Long liveId);
    Page<Live> findByKeyword(String keyword, Pageable pageable);
    Page<Live> findByArtistStageName(String keyword, Pageable pageable);

    Optional<Long> findActiveLiveIdByArtist(Long artistId);
    boolean isNonExistentLive(Long liveId);
    Live save(Live live);
    void saveActiveLiveInfo(String sessionId, String artistStageName);

    int findLiveCountByWeek(Long artistId, LocalDate endOfWeek);

    List<Live> findTopLivesByNumberOfViewers(int numberOfLives);

    void updateParticipantCount(String sessionId, int difference);

    Live updateLive(Live live);
    void deleteActiveLiveInfo(String sessionId);
}
