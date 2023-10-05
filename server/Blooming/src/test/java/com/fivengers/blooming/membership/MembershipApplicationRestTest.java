package com.fivengers.blooming.membership;

import static org.hamcrest.Matchers.equalTo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.artist.adapter.out.persistence.repository.ArtistSpringDataRepository;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.entity.Oauth;
import com.fivengers.blooming.member.adapter.out.persistence.repository.MemberSpringDataRepository;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.MemberRole;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipApplicationJpaEntity;
import com.fivengers.blooming.membership.adapter.out.persistence.repository.MembershipApplicationSpringDataRepository;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipApplicationModifyRequest;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipApplyRequest;
import com.fivengers.blooming.membership.domain.MembershipApplicationState;
import com.fivengers.blooming.support.RestEndToEndTest;
import io.restassured.RestAssured;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class MembershipApplicationRestTest extends RestEndToEndTest {

    @Autowired ArtistSpringDataRepository artistSpringDataRepository;
    @Autowired MemberSpringDataRepository memberSpringDataRepository;
    @Autowired
    MembershipApplicationSpringDataRepository membershipApplicationSpringDataRepository;
    MemberJpaEntity member;
    ArtistJpaEntity artist;
    MembershipApplicationJpaEntity membershipApplication;

    @BeforeEach
    void initObjects() {
        databaseCleaner.afterPropertiesSet();
        databaseCleaner.execute();
        member = memberSpringDataRepository.save(MemberJpaEntity.builder()
                .oauth(new Oauth(AuthProvider.KAKAO, "1234567"))
                .name("이지은")
                .nickname("아이유")
                .deleted(false)
                .role(List.of(MemberRole.ROLE_USER))
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
        LocalDateTime now = LocalDateTime.now();
        membershipApplication = membershipApplicationSpringDataRepository.save(
                MembershipApplicationJpaEntity.builder()
                        .title("아이유 (IU)")
                        .description("아이유입니다.")
                        .seasonStart(now)
                        .seasonEnd(now.plusYears(1))
                        .purchaseStart(now)
                        .purchaseEnd(now.plusMonths(1))
                        .saleCount(100)
                        .salePrice(1L)
                        .thumbnailUrl("https://image.com/iu")
                        .baseUri("https://base.com/iu")
                        .privateKey("test")
                        .applicationState(MembershipApplicationState.APPLY)
                        .deleted(false)
                        .artistJpaEntity(artist)
                        .build());
    }

    @Test
    @DisplayName("멤버십을 신청을 등록한다.")
    void createMembershipApplication() throws JsonProcessingException {
        LocalDateTime now = LocalDateTime.now();
        MembershipApplyRequest request = new MembershipApplyRequest("아이유 (IU)",
                "아이유입니다.",
                now,
                now.plusYears(1),
                now,
                now.plusMonths(1),
                100,
                1L,
                "https://image.com/iu",
                "https://blooming.com/base",
                "123456789");

        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken(member))
                .body(toJson(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/v1/membership-applications")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("자신의 멤버십 신청 현황을 조회한다.")
    void getMyMembershipApplicationDetails() {
        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken(member))
                .queryParam("state", "APPLY")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/v1/membership-applications/me")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("관리자가 멤버십 신청 목록을 조회한다.")
    void getMembershipApplicationListByAdmin() {
        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/v1/admin/membership-applications")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("관리자가 멤버십 신청 상태를 변경한다.")
    void modifyMembershipApplicationStateByAdmin() throws JsonProcessingException {
        MembershipApplicationModifyRequest request = new MembershipApplicationModifyRequest(
                MembershipApplicationState.APPROVAL);
        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken())
                .body(toJson(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put("/api/v1/admin/membership-applications/{applicationId}/states",
                        membershipApplication.getId())
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("results.applicationState",
                        response -> equalTo(request.applicationState().getValue()));
    }
}
