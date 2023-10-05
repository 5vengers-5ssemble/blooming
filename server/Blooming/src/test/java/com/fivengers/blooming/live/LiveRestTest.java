package com.fivengers.blooming.live;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.artist.adapter.out.persistence.repository.ArtistSpringDataRepository;
import com.fivengers.blooming.emoji.adapter.out.pesistence.entity.MotionModelJpaEntity;
import com.fivengers.blooming.emoji.adapter.out.pesistence.repository.MotionModelSpringDataRepository;
import com.fivengers.blooming.live.adapter.in.web.dto.LiveCreateRequest;
import com.fivengers.blooming.live.adapter.out.persistence.entity.LiveJpaEntity;
import com.fivengers.blooming.live.adapter.out.persistence.repository.LiveSpringDataRepository;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.entity.Oauth;
import com.fivengers.blooming.member.adapter.out.persistence.repository.MemberSpringDataRepository;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.MemberRole;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.NftSaleJpaEntity;
import com.fivengers.blooming.membership.adapter.out.persistence.repository.MembershipSpringDataRepository;
import com.fivengers.blooming.nft.adapter.out.persistence.entity.NftJpaEntity;
import com.fivengers.blooming.nft.adapter.out.persistence.entity.NftOwnerInfoJpaEntity;
import com.fivengers.blooming.nft.adapter.out.persistence.repository.NftOwnerInfoSpringDataRepository;
import com.fivengers.blooming.nft.adapter.out.persistence.repository.NftSpringDataRepository;
import com.fivengers.blooming.support.RestEndToEndTest;
import io.restassured.RestAssured;
import java.time.LocalDateTime;
import java.util.List;
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
    MembershipSpringDataRepository membershipSpringDataRepository;
    @Autowired
    MotionModelSpringDataRepository motionModelSpringDataRepository;
    @Autowired
    NftSpringDataRepository nftSpringDataRepository;
    @Autowired
    NftOwnerInfoSpringDataRepository nftOwnerInfoSpringDataRepository;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    LiveJpaEntity activeLive1;
    LiveJpaEntity activeLive2;
    LiveJpaEntity endedLive;
    ArtistJpaEntity artist1;
    ArtistJpaEntity artist2;
    MemberJpaEntity artistMember1;
    MemberJpaEntity artistMember2;
    MemberJpaEntity member1;
    MemberJpaEntity member2;
    NftJpaEntity nft;
    NftOwnerInfoJpaEntity nftOwnerInfo;
    MotionModelJpaEntity motionModelJpaEntity;
    MembershipJpaEntity membership;
    String sessionId;

    @BeforeEach
    void initObjects() {
        databaseCleaner.afterPropertiesSet();
        databaseCleaner.execute();
        LocalDateTime now = LocalDateTime.now();
        member1 = memberSpringDataRepository.save(MemberJpaEntity.builder()
                .oauth(new Oauth(AuthProvider.KAKAO, "1234567"))
                .name("Aron")
                .nickname("Aron")
                .deleted(false)
                .role(List.of(MemberRole.ROLE_USER))
                .build());
        member2 = memberSpringDataRepository.save(MemberJpaEntity.builder()
                .oauth(new Oauth(AuthProvider.KAKAO, "1234568"))
                .name("서원호")
                .nickname("서원호")
                .deleted(false)
                .role(List.of(MemberRole.ROLE_USER))
                .build());
        artistMember1 = memberSpringDataRepository.save(MemberJpaEntity.builder()
                .oauth(new Oauth(AuthProvider.KAKAO, "1234569"))
                .name("이지은")
                .nickname("아이유")
                .deleted(false)
                .role(List.of(MemberRole.ROLE_USER))
                .build());
        artistMember2 = memberSpringDataRepository.save(MemberJpaEntity.builder()
                .oauth(new Oauth(AuthProvider.KAKAO, "1234570"))
                .name("김찹쌀")
                .nickname("찹쌀")
                .deleted(false)
                .role(List.of(MemberRole.ROLE_USER))
                .build());
        artist1 = artistSpringDataRepository.save(ArtistJpaEntity.builder()
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .profileImageUrl("https://image.com")
                .youtubeUrl("https://youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/ui")
                .snsUrl("https://instagram.com/ui")
                .memberJpaEntity(artistMember1)
                .deleted(false)
                .build());
        artist2 = artistSpringDataRepository.save(ArtistJpaEntity.builder()
                .stageName("chapchap")
                .agency("Summary 엔터테인먼트")
                .description("귀염뽀짝 김찹쌀입니다.")
                .profileImageUrl("https://chap.image.com")
                .youtubeUrl("https://youtube.com/chap")
                .fanCafeUrl("https://cafe.daum.net/chap")
                .snsUrl("https://instagram.com/chap")
                .memberJpaEntity(artistMember2)
                .deleted(false)
                .build());
        activeLive1 = liveSpringDataRepository.save(LiveJpaEntity.builder()
                .title("IU의 서른번째 밤")
                .artistJpaEntity(artist1)
                .build());
        activeLive2 = liveSpringDataRepository.save(LiveJpaEntity.builder()
                .title("헬로우 찹쌀이")
                .artistJpaEntity(artist2)
                .build());
        endedLive = liveSpringDataRepository.save(LiveJpaEntity.builder()
                .title("헬로우 멍멍이")
                .endedAt(now)
                .artistJpaEntity(artist1)
                .build());
        membership = membershipSpringDataRepository.save(MembershipJpaEntity.builder()
                .title("iu")
                .symbol("IU")
                .description("아이유 멤버십1")
                .season(1)
                .seasonStart(now)
                .seasonEnd(now.plusMonths(1L))
                .purchaseStart(now)
                .purchaseEnd(now.plusMonths(1L))
                .saleCount(0)
                .salePrice(1L)
                .thumbnailUrl("https://image.com")
                .baseUri("https://base.com/")
                .contractAddress("0x123456789")
                .deleted(false)
                .artistJpaEntity(artist1)
                .nftSaleJpaEntity(NftSaleJpaEntity.builder()
                        .totalNftCount(1)
                        .soldNftCount(0)
                        .totalNftAmount(10000L)
                        .soldNftAmount(0L)
                        .deleted(false)
                        .build())
                .build());
        nft = nftSpringDataRepository.save(NftJpaEntity.builder()
                .tokenId("token")
                .symbol("symbol")
                .contractAddress("contractAddress")
                .deleted(false)
                .artist(artist1)
                .membership(membership)
                .build());
        nftOwnerInfo = nftOwnerInfoSpringDataRepository.save(NftOwnerInfoJpaEntity.builder()
                .owned(true)
                .memberJpaEntity(member1)
                .nftJpaEntity(nft)
                .build());
        motionModelSpringDataRepository.save(MotionModelJpaEntity.builder()
                .modelUrl("https://artistMotionModel.ai.com")
                .deleted(false)
                .build());
        sessionId = SESSION_PREFIX + activeLive1.getId();
    }

    @AfterEach
    void clearTestData() {
        redisTemplate.opsForHash().delete(REDIS_LIVE_STREAMER_KEY, sessionId);
        redisTemplate.opsForZSet().remove(REDIS_LIVE_VIEWER_COUNT_KEY, sessionId);
    }

    @Test
    @DisplayName("아티스트가 라이브를 등록한다.")
    void 아티스트가_라이브를_등록한다() throws JsonProcessingException {
        LiveCreateRequest request = new LiveCreateRequest("찹찹", artist1.getId(), "img/thumbnamil.png");
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
                .queryParam("artistId", artist1.getId())
                .when().get("/api/v1/lives/check/active")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("results.activeLiveId", response -> equalTo(activeLive1.getId().intValue()));

    }

    @Test
    @DisplayName("아티스트가 자신의 진행 중인 라이브를 종료한다.")
    void 아티스트가_자신의_진행_중인_라이브를_종료한다() {
        String sessionId = SESSION_PREFIX + activeLive1.getId();
        redisTemplate.opsForHash().put(REDIS_LIVE_STREAMER_KEY, sessionId,
                artist1.getStageName());
        redisTemplate.opsForZSet()
                .add(REDIS_LIVE_VIEWER_COUNT_KEY, sessionId, 5.0);

        RestAssured.given()
                .header(AUTHORIZATION, getAccessToken(artistMember1))
                .pathParam("liveId", activeLive1.getId())
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
        String sessionId = SESSION_PREFIX + activeLive1.getId();
        redisTemplate.opsForHash().put(REDIS_LIVE_STREAMER_KEY, sessionId,
                artist1.getStageName());
        redisTemplate.opsForZSet()
                .add(REDIS_LIVE_VIEWER_COUNT_KEY, sessionId, 5.0);

        RestAssured.given()
                .header(AUTHORIZATION, getAccessToken(member1))
                .pathParam("liveId", activeLive1.getId())
                .when().put("/api/v1/lives/{liveId}/close")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("results.errorCode", response -> equalTo("ERR_LIVE_005"));
    }

    @Test
    @DisplayName("nft를 구매한 아티스트의 진행 중인 라이브를 조회할 수 있다")
    void nft를_구매한_아티스트의_진행_중인_라이브를_조회할_수_있다() {
        RestAssured.given()
                .header(AUTHORIZATION, getAccessToken(member1))
                .when().get("/api/v1/lives/nft-purchased")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("results[0].id", response -> equalTo(activeLive1.getId().intValue()))
                .body("results", response -> hasSize(1));
    }


}
