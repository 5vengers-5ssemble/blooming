package com.fivengers.blooming.artist;

import static org.hamcrest.Matchers.equalTo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.artist.adapter.out.persistence.repository.ArtistSpringDataRepository;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistCreateRequest;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistVideoCreateRequest;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.entity.Oauth;
import com.fivengers.blooming.member.adapter.out.persistence.repository.MemberSpringDataRepository;
import com.fivengers.blooming.member.domain.AuthProvider;
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

public class ArtistRestTest extends RestEndToEndTest {

    @Autowired ArtistSpringDataRepository artistSpringDataRepository;
    @Autowired MemberSpringDataRepository memberSpringDataRepository;
    MemberJpaEntity member;
    ArtistJpaEntity artist;

    @BeforeEach
    void initObjects() {
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
    }

    @Test
    @DisplayName("아티스트 목록을 조회한다.")
    void getArtists() {
        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken())
                .when().get("/api/v1/artists")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("results[0].stageName", response -> equalTo(artist.getStageName()));
    }

    @Test
    @DisplayName("특정 아티스트를 조회한다.")
    void getArtistById() {
        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken())
                .when().get("/api/v1/artists/{artistId}", artist.getId())
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("results.stageName", response -> equalTo(artist.getStageName()));
    }

    @Test
    @DisplayName("아티스트를 생성한다.")
    void createArtist() throws JsonProcessingException {
        MemberJpaEntity member2 = memberSpringDataRepository.save(MemberJpaEntity.builder()
                .oauth(new Oauth(AuthProvider.KAKAO, "7654321"))
                .name("박효신")
                .nickname("박효신")
                .account("87654321")
                .deleted(false)
                .build());
        ArtistCreateRequest request = new ArtistCreateRequest("박효신",
                "몰라",
                "박효신입니다.",
                "https://image.com",
                "https://youtube.com/psh",
                "https://cafe.daum.net/psh",
                "https://instagram.com/psh",
                member2.getId(),
                new ArtistVideoCreateRequest(List.of("https://youtube.com/psh")));

        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken())
                .body(toJson(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/v1/admin/artists")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("results.stageName", response -> equalTo(request.stageName()));
    }
}
