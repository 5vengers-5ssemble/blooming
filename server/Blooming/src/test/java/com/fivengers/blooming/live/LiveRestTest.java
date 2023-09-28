package com.fivengers.blooming.live;

import static org.hamcrest.Matchers.equalTo;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.artist.adapter.out.persistence.repository.ArtistSpringDataRepository;
import com.fivengers.blooming.live.adapter.out.persistence.entity.LiveJpaEntity;
import com.fivengers.blooming.live.adapter.out.persistence.repository.LiveSpringDataRepository;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.entity.Oauth;
import com.fivengers.blooming.member.adapter.out.persistence.repository.MemberSpringDataRepository;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.support.RestEndToEndTest;
import io.restassured.RestAssured;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class LiveRestTest extends RestEndToEndTest {

    @Autowired
    LiveSpringDataRepository liveSpringDataRepository;
    @Autowired
    ArtistSpringDataRepository artistSpringDataRepository;
    @Autowired
    MemberSpringDataRepository memberSpringDataRepository;

    LiveJpaEntity activeLive;
    LiveJpaEntity endedLive;
    ArtistJpaEntity artist;
    MemberJpaEntity member;

    @BeforeEach
    void initObjects() {
        LocalDateTime now = LocalDateTime.now();
        member = memberSpringDataRepository.save(MemberJpaEntity.builder()
                .oauth(new Oauth(AuthProvider.KAKAO, "1234567"))
                .name("이지은")
                .nickname("아이유")
                .account("12345678")
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
                .memberJpaEntity(member)
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
                .body("results.activeLiveId", response -> equalTo(1));

    }

}
