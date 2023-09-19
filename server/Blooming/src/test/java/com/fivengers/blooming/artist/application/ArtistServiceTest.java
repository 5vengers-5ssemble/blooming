package com.fivengers.blooming.artist.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.fixture.artist.adapter.out.persistence.FakeArtistPersistenceAdapter;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ArtistServiceTest {

    FakeArtistPersistenceAdapter artistPersistenceAdapter;
    ArtistService artistService;

    @BeforeEach
    void initObjects() {
        this.artistPersistenceAdapter = new FakeArtistPersistenceAdapter();
        this.artistService = new ArtistService(this.artistPersistenceAdapter);
    }

    @Test
    @DisplayName("전체 아티스트를 조회한다.")
    void findAllArtists() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Artist artist = Artist.builder()
                .id(1L)
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .createdAt(now)
                .modifiedAt(now)
                .build();
        artistPersistenceAdapter.save(artist);

        // when
        List<Artist> artists = artistService.searchAll();

        // then
        assertThat(artists).hasSize(1);
        assertThat(artists).containsExactly(artist);
    }
}