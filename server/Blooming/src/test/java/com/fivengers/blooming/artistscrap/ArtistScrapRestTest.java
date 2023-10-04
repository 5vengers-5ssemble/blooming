package com.fivengers.blooming.artistscrap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.artist.adapter.out.persistence.repository.ArtistSpringDataRepository;
import com.fivengers.blooming.artistscrap.adapter.out.persistence.entity.ArtistScrapJpaEntity;
import com.fivengers.blooming.artistscrap.adapter.out.persistence.repository.ArtistScrapSpringDataRepository;
import com.fivengers.blooming.artistscrap.application.port.in.dto.ArtistScrapRequest;
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
import org.springframework.http.MediaType;

public class ArtistScrapRestTest extends RestEndToEndTest {

    @Autowired ArtistScrapSpringDataRepository artistScrapSpringDataRepository;
    @Autowired ArtistSpringDataRepository artistSpringDataRepository;
    @Autowired MemberSpringDataRepository memberSpringDataRepository;
    MemberJpaEntity member1;
    MemberJpaEntity member2;
    ArtistJpaEntity artist;
    ArtistScrapJpaEntity artistScrap;

    @BeforeEach
    void initObjects() {
        LocalDateTime now = LocalDateTime.now();
        member1 = memberSpringDataRepository.save(MemberJpaEntity.builder()
                .oauth(new Oauth(AuthProvider.KAKAO, "1234567"))
                .name("이지은")
                .nickname("아이유")
                .account("12345678")
                .deleted(false)
                .build());
        member2 = memberSpringDataRepository.save(MemberJpaEntity.builder()
                .oauth(new Oauth(AuthProvider.KAKAO, "7654321"))
                .name("박효신")
                .nickname("박효신")
                .account("1111111")
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
                .memberJpaEntity(member1)
                .deleted(false)
                .build());
        artistScrap = artistScrapSpringDataRepository.save(ArtistScrapJpaEntity.builder()
                .memberJpaEntity(member1)
                .artistJpaEntity(artist)
                .build());
    }

    @Test
    @DisplayName("스크랩 한다.")
    void scrap() throws JsonProcessingException {
        ArtistScrapRequest request = new ArtistScrapRequest(member2.getId());
        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/v1/artists/{artistId}/scrap", artist.getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("스크랩을 취소한다.")
    void unScrap() throws JsonProcessingException {
        ArtistScrapRequest request = new ArtistScrapRequest(member1.getId());
        RestAssured.given().log().all()
                .header(AUTHORIZATION, getAccessToken())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/v1/artists/{artistId}/unscrap", artist.getId())
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
