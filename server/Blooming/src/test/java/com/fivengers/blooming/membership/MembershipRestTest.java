package com.fivengers.blooming.membership;

import static org.hamcrest.Matchers.equalTo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.artist.adapter.out.persistence.repository.ArtistSpringDataRepository;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.entity.Oauth;
import com.fivengers.blooming.member.adapter.out.persistence.repository.MemberSpringDataRepository;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.NftSaleJpaEntity;
import com.fivengers.blooming.membership.adapter.out.persistence.repository.MembershipSpringDataRepository;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipCreateRequest;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipModifyRequest;
import com.fivengers.blooming.support.RestEndToEndTest;
import io.restassured.RestAssured;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class MembershipRestTest extends RestEndToEndTest {

    @Autowired MembershipSpringDataRepository membershipSpringDataRepository;
    @Autowired ArtistSpringDataRepository artistSpringDataRepository;
    @Autowired MemberSpringDataRepository memberSpringDataRepository;
    MemberJpaEntity member;
    ArtistJpaEntity artist;
    MembershipJpaEntity membership;

    @BeforeEach
    void initObjects() {
        databaseCleaner.afterPropertiesSet();
        databaseCleaner.execute();
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
        membership = membershipSpringDataRepository.save(MembershipJpaEntity.builder()
                .title("아이유 멤버십 시즌1")
                .description("아이유 멤버십1")
                .season(1)
                .seasonStart(now)
                .seasonEnd(now.plusMonths(1L))
                .purchaseStart(now)
                .purchaseEnd(now.plusMonths(1L))
                .saleCount(0)
                .thumbnailUrl("https://image.com")
                .deleted(false)
                .artistJpaEntity(artist)
                .nftSaleJpaEntity(NftSaleJpaEntity.builder()
                        .totalNftCount(1)
                        .soldNftCount(0)
                        .totalNftAmount(10000L)
                        .soldNftAmount(0L)
                        .deleted(false)
                        .build())
                .build());
    }

    @Test
    @DisplayName("멤버십 목록을 최신순으로 조회한다.")
    void getMembershipBySortingCreatedAt() {
        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken())
                .when().get("/api/v1/memberships?page=0&size=20&sort=createdAt,desc")
                .then().log().all()
                .statusCode(200)
                .body("results.content[0].title", response -> equalTo(membership.getTitle()));
    }

    @Test
    @DisplayName("진행 중인 멤버십 목록을 조회한다.")
    void getMembershipByOngoing() {
        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken())
                .when().get("/api/v1/memberships/ongoing?page=0&size=20&sort=createdAt,desc")
                .then().log().all()
                .statusCode(200)
                .body("results.content[0].title", response -> equalTo(membership.getTitle()));
    }

    @Test
    @DisplayName("멤버십을 등록한다")
    void createMembership() throws JsonProcessingException {
        LocalDateTime now = LocalDateTime.now();
        MembershipCreateRequest request = new MembershipCreateRequest("아이유 (IU)",
                "아이유입니다.",
                1,
                now,
                now.plusYears(1),
                now,
                now.plusMonths(1),
                "https://image.com/iu",
                artist.getId());

        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken())
                .body(toJson(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/v1/admin/memberships")
                .then().log().all()
                .statusCode(200)
                .body("results.title", response -> equalTo(request.title()));
    }

    @Test
    @DisplayName("가장 많이 판 3개의 멤버십(베스트 멤버십)을 조회한다.")
    void getBestMemberships() {
        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken())
                .when().get("/api/v1/memberships/best")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("멤버십을 아티스트명으로 검색한다.")
    void getMembershipByArtistNameContainsQuery() {
        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken())
                .queryParam("query", "이유")
                .when().get("/api/v1/memberships/search")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("멤버십을 수정한다.")
    void modifyMembership() throws JsonProcessingException {
        LocalDateTime now = LocalDateTime.now();
        MembershipModifyRequest request = new MembershipModifyRequest(membership.getId(),
                "박효신",
                "박효신입니다.",
                now,
                now.plusYears(1),
                now,
                now.plusMonths(1),
                "https://image.com/psh");

        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken(member))
                .body(toJson(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put("/api/v1/memberships/{membershipId}", membership.getId())
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("results.title", response -> equalTo(request.title()));
    }

}
