package com.fivengers.blooming.live.application.port;

import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.global.exception.artist.ArtistNotFoundException;
import com.fivengers.blooming.global.exception.live.LiveNotFoundException;
import com.fivengers.blooming.global.exception.live.SessionNotFoundException;
import com.fivengers.blooming.global.util.DateUtils;
import com.fivengers.blooming.live.adapter.in.web.dto.ConnectionTokenDetailRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.LiveCreateRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.LiveFrequencyDetailsRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.SessionDetailRequest;
import com.fivengers.blooming.live.application.port.in.LiveArtistUseCase;
import com.fivengers.blooming.live.application.port.in.LiveSearchUseCase;
import com.fivengers.blooming.live.application.port.in.LiveSessionUseCase;
import com.fivengers.blooming.live.application.port.out.LivePort;
import com.fivengers.blooming.live.domain.Live;
import com.fivengers.blooming.live.domain.LiveFrequency;
import com.fivengers.blooming.live.domain.SessionId;
import io.openvidu.java.client.Connection;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Session;
import io.openvidu.java.client.SessionProperties;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LiveService implements LiveSearchUseCase, LiveSessionUseCase, LiveArtistUseCase {

    private final LivePort livePort;
    private final ArtistPort artistPort;

    private OpenVidu openVidu;
    @Value("${openvidu.url}")
    private String OPENVIDU_URL;

    @Value("${openvidu.secret}")
    private String OPENVIDU_SECRET;

    @PostConstruct
    public void init() {
        this.openVidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    @Override
    public Page<Live> searchByKeyword(String query, Pageable pageable) {
        return livePort.findByKeyword(query, pageable);
    }

    @Override
    public Page<Live> searchByArtist(String query, Pageable pageable) {
        return livePort.findByArtistStageName(query, pageable);
    }

    @Override
    public String createSession(SessionDetailRequest sessionDetailRequest)
            throws OpenViduJavaClientException, OpenViduHttpException {
        // TODO : NFT 가지고 있는지 검증 로직 NFT 구현 후 추가 예정
        validateLive(sessionDetailRequest);

        SessionProperties properties = SessionProperties
                .fromJson(sessionDetailRequest.toMap())
                .build();
        Session session = openVidu.createSession(properties);
        return session.getSessionId();
    }

    private void validateLive(SessionDetailRequest sessionDetailRequest) {
        SessionId sessionId = new SessionId(sessionDetailRequest.customSessionId());
        Long liveId = sessionId.getLiveId();
        if (livePort.isNonExistentLive(liveId)) {
            throw new LiveNotFoundException();
        }
    }

    @Override
    public String createConnection(ConnectionTokenDetailRequest connectionTokenDetailRequest)
            throws OpenViduJavaClientException, OpenViduHttpException {
        Session session = openVidu.getActiveSession(connectionTokenDetailRequest.sessionId());
        if (session == null) {
            throw new SessionNotFoundException();
        }
        Connection connection = session.createConnection();
        return connection.getToken();
    }

    @Override
    public String searchSessionId(Long liveId) {
        return SessionId.makeSessionId(liveId);
    }

    @Override
    public Live createLive(LiveCreateRequest liveCreateRequest) {
        Artist artist = artistPort.findById(liveCreateRequest.artistId())
                .orElseThrow(ArtistNotFoundException::new);
        Live live = Live.builder()
                .title(liveCreateRequest.liveTitle())
                .artist(artist)
                .build();
        return livePort.save(live);
    }

    @Override
    public List<LiveFrequency> searchLiveFrequencyByArtist(
            LiveFrequencyDetailsRequest liveFrequencyDetailsRequest) {
        if (artistPort.findById(liveFrequencyDetailsRequest.artistId()).isEmpty()) {
            throw new ArtistNotFoundException();
        }

        // 현재날짜를 기준으로 바로 이전 일요일 날짜를 가져온다.
        LocalDate lastSunday = DateUtils.findLastSunday();
        return IntStream.range(0, liveFrequencyDetailsRequest.numberOfWeeks())
                .mapToObj(i -> {
                    LocalDate prevLastSunday = lastSunday.minusDays(i * 7L);
                    return LiveFrequency.of(
                            prevLastSunday,
                            livePort.findLiveCountByWeek(
                                    liveFrequencyDetailsRequest.artistId(),
                                    prevLastSunday
                            )
                    );
                }).toList();
    }
}
