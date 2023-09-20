package com.fivengers.blooming.project.adapter.out.persistence.repository;


import static org.assertj.core.api.Assertions.assertThat;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.artist.adapter.out.persistence.repository.ArtistSpringDataRepository;
import com.fivengers.blooming.project.adapter.out.persistence.entity.ConcertJpaEntity;
import com.fivengers.blooming.project.domain.Concert;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConcertPersistenceAdapterTest {

    @Autowired
    private ConcertPersistenceAdapter concertPersistenceAdapter;
    @Autowired
    private ConcertSpringDataRepository concertSpringDataRepository;
    @Autowired
    private ArtistSpringDataRepository artistSpringDataRepository;

    @Test
    @DisplayName("전체 콘서트 펀딩 프로젝트 목록을 조회한다.")
    void findAllConcerts() {
        // given
        LocalDateTime now = LocalDateTime.now();
        ArtistJpaEntity artist = ArtistJpaEntity.builder().build();
        artistSpringDataRepository.save(artist);
        ArtistJpaEntity artist2 = ArtistJpaEntity.builder().build();
        artistSpringDataRepository.save(artist2);

        ConcertJpaEntity concert1 = ConcertJpaEntity.builder()
                .name("아이유 데뷔 20주년 콘서트")
                .startedAt(now)
                .endedAt(now.plusMonths(3L))
                .description("아이유 데뷔 20주년을 기념하는 콘서트")
                .artist(artist)
                .build();
        concertSpringDataRepository.save(concert1);
        ConcertJpaEntity concert2 = ConcertJpaEntity.builder()
                .name("아이유 데뷔 21주년 콘서트")
                .startedAt(now)
                .endedAt(now.plusMonths(3L))
                .description("아이유 데뷔 20주년을 기념하는 콘서트")
                .artist(artist)
                .build();
        concertSpringDataRepository.save(concert2);
        List<Concert> concerts = concertPersistenceAdapter.findAll(null);
        assertThat(concerts).hasSize(2);
    }
}
