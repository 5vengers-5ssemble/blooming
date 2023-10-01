package com.fivengers.blooming.live.application;

import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.global.exception.artist.ArtistNotFoundException;
import com.fivengers.blooming.global.exception.live.LiveNotFoundException;
import com.fivengers.blooming.global.exception.live.SessionNotFoundException;
import com.fivengers.blooming.global.exception.live.UnauthorizedMemberForClosingLiveException;
import com.fivengers.blooming.global.util.Assertion;
import com.fivengers.blooming.global.util.DateUtils;
import com.fivengers.blooming.live.adapter.in.web.dto.ConnectionTokenDetailRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.LiveCreateRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.LiveFrequencyDetailsRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.OpenviduWebhookRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.SessionDetailRequest;
import com.fivengers.blooming.live.application.SessionId;
import com.fivengers.blooming.live.application.port.in.LiveArtistUseCase;
import com.fivengers.blooming.live.application.port.in.LiveSearchUseCase;
import com.fivengers.blooming.live.application.port.in.LiveSessionUseCase;
import com.fivengers.blooming.live.application.port.out.LivePort;
import com.fivengers.blooming.live.domain.Live;
import com.fivengers.blooming.live.domain.LiveFrequency;
import com.fivengers.blooming.member.domain.Member;
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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LiveService implements LiveSearchUseCase, LiveSessionUseCase, LiveArtistUseCase {

    private static final Long NOT_EXIST_ID = -1L;


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
    public Page<Live> searchActiveLive(Pageable pageable) {
        return livePort.findActiveLive(pageable);
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
        Long liveId = SessionId.getLiveId(sessionDetailRequest.customSessionId());
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

    @Transactional
    @Override
    public Live createLive(LiveCreateRequest liveCreateRequest) {
        Artist artist = artistPort.findById(liveCreateRequest.artistId())
                .orElseThrow(ArtistNotFoundException::new);
        Live live = Live.builder()
                .title(liveCreateRequest.liveTitle())
                .artist(artist)
                .build();

        Live createdLive = livePort.save(live);

        livePort.saveActiveLiveInfo(createdLive.getSessionId(), createdLive.getArtist().getStageName());
        return createdLive;
    }

    @Transactional
    @Override
    public Live closeLive(Long liveId, Member member) {
        // 해당 멤버가 해당 live를 오픈한 아티스트인지 검증
        Live live = livePort.findActiveLiveById(liveId).orElseThrow(LiveNotFoundException::new);
        Assertion.with(member.getId())
                .setValidation(live::canCloseLive)
                .validateOrThrow(UnauthorizedMemberForClosingLiveException::new);

        // 해당 라이브의 종료일 설정
        live.close();
        Live closedLive = livePort.updateLive(live);

        // 레디스에서 해당 라이브 관련 정보 삭제
        livePort.deleteActiveLiveInfo(closedLive.getSessionId());

        return closedLive;
    }

    @Override
    public List<LiveFrequency> searchLiveFrequencyByArtist(
            LiveFrequencyDetailsRequest liveFrequencyDetailsRequest) {
        if (artistPort.findById(liveFrequencyDetailsRequest.artistId()).isEmpty()) {
            throw new ArtistNotFoundException();
        }

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

    @Override
    public Long checkActiveLive(Long artistId) {
        return livePort.findActiveLiveIdByArtist(artistId).orElse(NOT_EXIST_ID);
    }

    @Override
    public List<Live> searchBestLive(int numberOfLives) {
        return livePort.findTopLivesByNumberOfViewers(numberOfLives);
    }

    @Override
    public void addParticipantCount(OpenviduWebhookRequest openviduWebhookRequest) {
        // sessionId 객체를 만들면서 유효한 sessionId인지 검증도 진행합니다.
        String requestedSessionId = openviduWebhookRequest.sessionId();
        SessionId.validate(requestedSessionId);
        livePort.updateParticipantCount(requestedSessionId, 1);
    }

    @Override
    public void removeParticipantCount(OpenviduWebhookRequest openviduWebhookRequest) {
        // sessionId 객체를 만들면서 유효한 sessionId인지 검증도 진행합니다.
        String requestedSessionId = openviduWebhookRequest.sessionId();
        SessionId.validate(requestedSessionId);
        livePort.updateParticipantCount(requestedSessionId, -1);
    }
}
