package com.fivengers.blooming.artist;

import static org.hamcrest.Matchers.equalTo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistApplicationJpaEntity;
import com.fivengers.blooming.artist.adapter.out.persistence.repository.ArtistApplicationQueryRepository;
import com.fivengers.blooming.artist.adapter.out.persistence.repository.ArtistApplicationSpringDataRepository;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistApplicationModifyRequest;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistApplyRequest;
import com.fivengers.blooming.artist.domain.ArtistApplicationState;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.entity.Oauth;
import com.fivengers.blooming.member.adapter.out.persistence.repository.MemberSpringDataRepository;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.support.RestEndToEndTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class ArtistApplicationRestTest extends RestEndToEndTest {

    @Autowired
    ArtistApplicationSpringDataRepository artistApplicationSpringDataRepository;
    @Autowired
    ArtistApplicationQueryRepository artistApplicationQueryRepository;
    @Autowired
    MemberSpringDataRepository memberSpringDataRepository;

    MemberJpaEntity memberJpaEntity;
    ArtistApplicationJpaEntity artistApplication;

    @BeforeEach
    void initObjects() {
        memberJpaEntity = memberSpringDataRepository.save(MemberJpaEntity.builder()
                .oauth(new Oauth(AuthProvider.KAKAO, "1234567"))
                .name("이지은")
                .nickname("아이유")
                .deleted(false)
                .build());
        artistApplication = artistApplicationSpringDataRepository.save(
                ArtistApplicationJpaEntity.builder()
                        .stageName("아이유 (IU)")
                        .description("아이유입니다.")
                        .agency("EDAM 엔터테인먼트")
                        .applicationState(ArtistApplicationState.APPLY)
                        .profileImageUrl("https://image.com/iu")
                        .youtubeUrl("https://www.youtube.com/iu")
                        .fanCafeUrl("https://cafe.daum.net/iu")
                        .snsUrl("https://instagram.com/iu")
                        .memberJpaEntity(memberJpaEntity)
                        .build());
    }

    @AfterEach
    void clearData() {
        artistApplicationSpringDataRepository.deleteAll();
    }

    @Test
    @DisplayName("아티스트 신청 목록을 조회한다.")
    void getArtistApplications() {
        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken())
                .queryParam("page", 0)
                .queryParam("size", 10)
                .queryParam("sort", "createdAt")
                .queryParam("state", "APPLY")
                .when().get("/api/v1/admin/artist-applications")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("results.content[0].id",
                        response -> equalTo(artistApplication.getId().intValue()));
    }

    @Test
    @DisplayName("아티스트 신청 상태를 변경한다.")
    void putArtistApplicationState() throws JsonProcessingException {
        ArtistApplicationModifyRequest request =
                new ArtistApplicationModifyRequest(ArtistApplicationState.APPROVAL);

        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(toJson(request))
                .when().put("/api/v1/admin/artist-applications/{applicationId}/states",
                        artistApplication.getId())
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("results.applicationState",
                        response -> equalTo(request.applicationState().getValue()));
    }

    @Test
    @DisplayName("아티스트 신청을 등록한다.")
    void postArtistApplication() throws JsonProcessingException {
        ArtistApplyRequest request = new ArtistApplyRequest(
                "아이유 (IU)",
                "EDAM 엔터테인먼트",
                "아이유입니다.",
                "https://image.com/iu",
                "https://www.youtube.com/iu",
                "https://cafe.daum.net/iu",
                "https://instagram.com/iu");

        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(toJson(request))
                .when().post("/api/v1/artist-applications")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("results.stageName", response -> equalTo(request.stageName()))
                .body("results.applicationState",
                        response -> equalTo(ArtistApplicationState.APPLY.getValue()));
    }

    @Test
    @DisplayName("자신의 아티스트 신청 현황을 조회한다.")
    void getMyArtistApplication() {
        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken(memberJpaEntity))
                .when().get("/api/v1/artist-applications/me")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
}
