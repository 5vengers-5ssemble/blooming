package com.fivengers.blooming.live;

import static org.hamcrest.Matchers.equalTo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.artist.adapter.out.persistence.repository.ArtistSpringDataRepository;
import com.fivengers.blooming.emoji.adapter.out.pesistence.entity.MotionModelJpaEntity;
import com.fivengers.blooming.emoji.adapter.out.pesistence.repository.MotionModelSpringDataRepository;
import com.fivengers.blooming.emoji.domain.Motion;
import com.fivengers.blooming.live.adapter.in.web.dto.LiveCreateRequest;
import com.fivengers.blooming.live.adapter.out.persistence.entity.LiveJpaEntity;
import com.fivengers.blooming.live.adapter.out.persistence.repository.LiveSpringDataRepository;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.entity.Oauth;
import com.fivengers.blooming.member.adapter.out.persistence.repository.MemberSpringDataRepository;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.support.RestEndToEndTest;
import io.restassured.RestAssured;
import java.time.LocalDateTime;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class LiveRestTest extends RestEndToEndTest {

    private final String REDIS_LIVE_VIEWER_COUNT_KEY = "liveViewerCount";
    private final String REDIS_LIVE_STREAMER_KEY = "liveStreamer";
    private final String SESSION_PREFIX = "blooming";

    @Autowired
    LiveSpringDataRepository liveSpringDataRepository;
    @Autowired
    ArtistSpringDataRepository artistSpringDataRepository;
    @Autowired
    MemberSpringDataRepository memberSpringDataRepository;
    @Autowired
    MotionModelSpringDataRepository motionModelSpringDataRepository;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    LiveJpaEntity activeLive;
    LiveJpaEntity endedLive;
    ArtistJpaEntity artist;
    MemberJpaEntity artistMember;
    MemberJpaEntity member;
    MotionModelJpaEntity motionModelJpaEntity;
    String sessionId;

    @BeforeEach
    void initObjects() {
        LocalDateTime now = LocalDateTime.now();
        member = memberSpringDataRepository.save(MemberJpaEntity.builder()
                .oauth(new Oauth(AuthProvider.KAKAO, "1234567"))
                .name("Aron")
                .nickname("Aron")
                .deleted(false)
                .build());
        artistMember = memberSpringDataRepository.save(MemberJpaEntity.builder()
                .oauth(new Oauth(AuthProvider.KAKAO, "1234567"))
                .name("이지은")
                .nickname("아이유")
                .deleted(false)
                .build());
        artist = artistSpringDataRepository.save(ArtistJpaEntity.builder()
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .profileImageUrl("https://image.com")
                .youtubeUrl("https://youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/ui")
                .snsUrl("https://instagram.com/ui")
                .memberJpaEntity(artistMember)
                .deleted(false)
                .build());
        activeLive = liveSpringDataRepository.save(LiveJpaEntity.builder()
                .title("헬로우 찹쌀이")
                .artistJpaEntity(artist)
                .build());
        endedLive = liveSpringDataRepository.save(LiveJpaEntity.builder()
                .title("헬로우 멍멍이")
                .endedAt(now)
                .artistJpaEntity(artist)
                .build());
        motionModelSpringDataRepository.save(MotionModelJpaEntity.builder()
                .modelUrl("https://artistMotionModel.ai.com")
                .deleted(false)
                .build());
        sessionId = SESSION_PREFIX + activeLive.getId();
    }

    @AfterEach
    void clearTestData() {
        redisTemplate.opsForHash().delete(REDIS_LIVE_STREAMER_KEY, sessionId);
        redisTemplate.opsForZSet().remove(REDIS_LIVE_VIEWER_COUNT_KEY, sessionId);
    }

    @Test
    @DisplayName("아티스트가 라이브를 등록한다.")
    void 아티스트가_라이브를_등록한다() throws JsonProcessingException {
        LiveCreateRequest request = new LiveCreateRequest("찹찹", 1L, "img/thumbnamil.png");
        RestAssured.given()
                .header(AUTHORIZATION, getAccessToken())
                .body(toJson(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/v1/lives")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("results.title", response -> equalTo(request.liveTitle()))
                .body("results.artist.id", response -> equalTo(request.artistId().intValue()))
                .body("results.thumbnailUrl", response -> equalTo(request.thumbnailUrl()));
    }

    @Test
    @DisplayName("아티스트의 진행중인 라이브가 있을 경우 해당 라이브의 Id를 반환한다.")
    void 아티스트의_진행중인_라이브가_있을_경우_해당_라이브의_Id를_반환한다() {
        RestAssured.given()
                .header(AUTHORIZATION, getAccessToken())
                .queryParam("artistId", artist.getId())
                .when().get("/api/v1/lives/check/active")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("results.activeLiveId", response -> equalTo(activeLive.getId().intValue()));

    }

    @Test
    @DisplayName("아티스트가 자신의 진행 중인 라이브를 종료한다.")
    void 아티스트가_자신의_진행_중인_라이브를_종료한다() {
        String sessionId = SESSION_PREFIX + activeLive.getId();
        redisTemplate.opsForHash().put(REDIS_LIVE_STREAMER_KEY, sessionId,
                artist.getStageName());
        redisTemplate.opsForZSet()
                .add(REDIS_LIVE_VIEWER_COUNT_KEY, sessionId, 5.0);

        RestAssured.given()
                .header(AUTHORIZATION, getAccessToken(artistMember))
                .pathParam("liveId", activeLive.getId())
                .when().put("/api/v1/lives/{liveId}/close")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("results.id", response -> equalTo(1));

        SoftAssertions.assertSoftly(as -> {
            as.assertThat(redisTemplate.opsForHash().hasKey(REDIS_LIVE_STREAMER_KEY, sessionId)).isFalse();
            as.assertThat(redisTemplate.opsForZSet().score(REDIS_LIVE_VIEWER_COUNT_KEY, sessionId)).isNull();
        });
    }
    @Test
    @DisplayName("라이브를 종료할 권한이 없는 사용자가 라이브 종료 요청 시 에러가 발생한다.")
    void 라이브를_종료할_권한이_없는_사용자가_라이브_종료_요청_시_에러가_발생한다() {
        String sessionId = SESSION_PREFIX + activeLive.getId();
        redisTemplate.opsForHash().put(REDIS_LIVE_STREAMER_KEY, sessionId,
                artist.getStageName());
        redisTemplate.opsForZSet()
                .add(REDIS_LIVE_VIEWER_COUNT_KEY, sessionId, 5.0);

        RestAssured.given()
                .header(AUTHORIZATION, getAccessToken(member))
                .pathParam("liveId", activeLive.getId())
                .when().put("/api/v1/lives/{liveId}/close")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("results.errorCode", response -> equalTo("ERR_LIVE_005"));
    }


}
