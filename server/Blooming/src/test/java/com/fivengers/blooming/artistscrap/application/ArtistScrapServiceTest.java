package com.fivengers.blooming.artistscrap.application;

import static org.assertj.core.api.Assertions.allOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artistscrap.application.port.in.dto.ArtistScrapRequest;
import com.fivengers.blooming.artistscrap.application.port.out.ArtistScrapPort;
import com.fivengers.blooming.artistscrap.application.port.out.ArtistScrapRecordPort;
import com.fivengers.blooming.artistscrap.domain.ArtistScrap;
import com.fivengers.blooming.fixture.artist.adapter.out.persistence.FakeArtistPersistenceAdapter;
import com.fivengers.blooming.fixture.artistscrap.adapter.out.persistence.FakeArtistScrapPersistenceAdapter;
import com.fivengers.blooming.fixture.artistscrap.adapter.out.persistence.FakeArtistScrapRecordPersistenceAdapter;
import com.fivengers.blooming.fixture.member.adapter.out.persistence.FakeMemberPersistenceAdapter;
import com.fivengers.blooming.global.exception.artistscrap.ArtistScrapNotFoundException;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberRole;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArtistScrapServiceTest {

    ArtistScrapService artistScrapService;
    ArtistScrapRecordService artistScrapRecordService;
    ArtistScrapRecordPort artistScrapRecordPort;
    ArtistScrapPort artistScrapPort;
    FakeMemberPersistenceAdapter memberPersistenceAdapter;
    ArtistPort artistPort;
    Member member;
    Artist artist;

    @BeforeEach
    void initObjects() {
        this.artistScrapRecordPort = new FakeArtistScrapRecordPersistenceAdapter();
        this.artistScrapRecordService = new ArtistScrapRecordService(this.artistScrapRecordPort);
        this.artistScrapPort = new FakeArtistScrapPersistenceAdapter();
        this.memberPersistenceAdapter = new FakeMemberPersistenceAdapter();
        this.artistPort = new FakeArtistPersistenceAdapter();
        this.artistScrapService = new ArtistScrapService(this.artistScrapRecordService,
                                                         this.artistScrapPort,
                                                         this.memberPersistenceAdapter,
                                                         this.artistPort);
        LocalDateTime now = LocalDateTime.now();
        this.member = Member.builder()
                .id(1L)
                .oauthProvider(AuthProvider.KAKAO)
                .oauthAccount("12434512")
                .name("이지은")
                .nickname("아이유")
                .createdAt(now)
                .modifiedAt(now)
                .role(List.of(MemberRole.ROLE_USER))
                .build();
        memberPersistenceAdapter.save(this.member);
        this.artist = Artist.builder()
                .id(1L)
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .profileImageUrl("https://image.com")
                .youtubeUrl("https://youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/ui")
                .snsUrl("https://instagram.com/ui")
                .createdAt(now)
                .modifiedAt(now)
                .member(member)
                .build();
        artistPort.save(this.artist);
    }

    @Test
    @DisplayName("아티스트를 스크랩한다.")
    void scrap() {
        artistScrapService.scrap(artist.getId(), member.getId());

        Optional<ArtistScrap> artistScrap = artistScrapPort
                .findByMemberIdAndArtistId(member.getId(), artist.getId());

        assertThat(artistScrap).isNotEmpty();
        SoftAssertions.assertSoftly(as -> {
            as.assertThat(artistScrap.get().getMember().getId()).isEqualTo(member.getId());
            as.assertThat(artistScrap.get().getArtist().getId()).isEqualTo(artist.getId());
        });

    }

    @Test
    @DisplayName("아티스트를 스크랩을 취소한다.")
    void unScrap() {
        artistScrapService.scrap(artist.getId(), member.getId());
        artistScrapService.unScrap(artist.getId(), member.getId());

        Optional<ArtistScrap> artistScrap = artistScrapPort.findByMemberIdAndArtistId(
                member.getId(), artist.getId());

        assertThat(artistScrap).isEmpty();
    }

}