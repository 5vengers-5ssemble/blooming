package com.fivengers.blooming.artist.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.fivengers.blooming.artist.application.port.in.dto.ArtistCreateRequest;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistVideoCreateRequest;
import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.artist.application.port.out.ArtistVideoPort;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.fixture.artist.adapter.out.persistence.FakeArtistPersistenceAdapter;
import com.fivengers.blooming.fixture.artist.adapter.out.persistence.FakeArtistVideoPersistenceAdapter;
import com.fivengers.blooming.fixture.member.adapter.out.persistence.FakeMemberPersistenceAdapter;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberRole;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ArtistServiceTest {

    ArtistPort artistPort;
    ArtistVideoPort artistVideoPort;
    FakeMemberPersistenceAdapter memberPersistenceAdapter;
    ArtistService artistService;
    Member member;

    @BeforeEach
    void initObjects() {
        this.artistPort = new FakeArtistPersistenceAdapter();
        this.artistVideoPort = new FakeArtistVideoPersistenceAdapter();
        this.memberPersistenceAdapter = new FakeMemberPersistenceAdapter();
        this.artistService = new ArtistService(this.artistPort,
                                               this.artistVideoPort,
                                               this.memberPersistenceAdapter);
        LocalDateTime now = LocalDateTime.now();
        member = memberPersistenceAdapter.save(Member.builder()
                .id(1L)
                .oauthProvider(AuthProvider.KAKAO)
                .oauthAccount("1234567")
                .name("mock")
                .nickname("mock")
                .account("7654321")
                .createdAt(now)
                .modifiedAt(now)
                .role(List.of(MemberRole.ROLE_USER))
                .build());
    }

    @Test
    @DisplayName("전체 아티스트를 조회한다.")
    void searchAllArtists() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Artist artist = Artist.builder()
                .id(1L)
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .profileImageUrl("https://image.com")
                .youtubeUrl("https://youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/iu")
                .snsUrl("https://instagram.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .member(member)
                .build();
        artistPort.save(artist);

        // when
        List<Artist> artists = artistService.searchAll();

        // then
        assertThat(artists).hasSize(1);
        assertThat(artists).containsExactly(artist);
    }

    @Test
    @DisplayName("ID에 해당하는 아티스트를 조회한다.")
    void searchArtistById() {
        LocalDateTime now = LocalDateTime.now();
        Artist artist = Artist.builder()
                .id(1L)
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .profileImageUrl("https://image.com")
                .youtubeUrl("https://youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/iu")
                .snsUrl("https://instagram.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .member(member)
                .build();
        artistPort.save(artist);

        Artist findArtist = artistService.searchById(1L);

        assertThat(findArtist.getId()).isEqualTo(artist.getId());
    }

    @Test
    @DisplayName("아티스트를 등록한다.")
    void addArtist() {
        LocalDateTime now = LocalDateTime.now();
        ArtistCreateRequest request = new ArtistCreateRequest("아이유",
                "EDAM 엔터테인먼트",
                "아이유입니다.",
                "https://image.com",
                "https://youtube.com/iu",
                "https://cafe.daum.net/iu",
                "https://instagram.com/iu",
                member.getId(),
                new ArtistVideoCreateRequest(List.of("https://youtube.com/iu")));
        Artist artist = Artist.builder()
                .id(1L)
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .profileImageUrl("https://image.com")
                .youtubeUrl("https://youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/iu")
                .snsUrl("https://instagram.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .member(member)
                .build();


        Artist savedArtist = artistService.add(request);

        assertThat(savedArtist.getId()).isEqualTo(artist.getId());
    }
}