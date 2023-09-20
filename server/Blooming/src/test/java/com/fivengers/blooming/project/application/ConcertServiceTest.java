package com.fivengers.blooming.project.application;


import static org.assertj.core.api.Assertions.assertThat;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.fixture.project.adapter.out.persistence.FakeConcertPersistenceAdapter;
import com.fivengers.blooming.project.application.service.ConcertService;
import com.fivengers.blooming.project.domain.Concert;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


public class ConcertServiceTest {

    FakeConcertPersistenceAdapter concertPersistenceAdapter;
    ConcertService concertService;

    @BeforeEach
    void beforeEach() {
        this.concertPersistenceAdapter = new FakeConcertPersistenceAdapter();
        this.concertService = new ConcertService(concertPersistenceAdapter);
    }

    @Test
    @DisplayName("전체 콘서트 펀딩 프로젝트를 펀딩 모금액(인기)순으로 정렬하여 조회한다.")
    void searchAllConcertsSortByFundingAmount() {
        // given
        List<Concert> concerts = createConcertList();
        // when
        List<Concert> found = concertService.searchAll(PageRequest.of(0, 10, Sort.by("fundingAmount").descending()));
        //then
        assertThat(found).hasSize(3);
        assertThat(found).containsExactly(concerts.get(2), concerts.get(1), concerts.get(0));
    }

    @Test
    @DisplayName("전체 콘서트 펀딩 프로젝트를 생성일(최신)순으로 정렬하여 조회한다.")
    void searchAllConcertsSortByCreatedAt() {
        // given
        List<Concert> concerts = createConcertList();
        // when
        List<Concert> found = concertService.searchAll(PageRequest.of(0, 10, Sort.by("createdAt").ascending()));
        //then
        assertThat(found).hasSize(3);
        assertThat(found).containsExactly(concerts.get(0), concerts.get(1), concerts.get(2));
    }

    @Test
    @DisplayName("모집중인 전체 콘서트 펀딩 프로젝트를 펀딩 모금액(인기)순으로 정렬하여 조회한다.")
    void searchAllOngoingConcertsSortByFundingAmount() {
        // given
        List<Concert> concerts = createConcertList();
        // when
        List<Concert> found = concertService.searchAllOngoingProject(PageRequest.of(0, 10, Sort.by("fundingAmount").descending()));
        //then
        assertThat(found).hasSize(2);
        assertThat(found).containsExactly(concerts.get(2), concerts.get(1));
    }

    @Test
    @DisplayName("모집중인 전체 콘서트 펀딩 프로젝트를 생성일(최신)순으로 정렬하여 조회한다.")
    void searchAllOngoingConcertsSortByCreatedAt() {
        // given
        List<Concert> concerts = createConcertList();
        // when
        List<Concert> found = concertService.searchAllOngoingProject(PageRequest.of(0, 10, Sort.by("createdAt").ascending()));
        //then
        assertThat(found).hasSize(2);
        assertThat(found).containsExactly(concerts.get(1), concerts.get(2));
    }

    private List<Concert> createConcertList() {
        LocalDateTime now = LocalDateTime.now();
        List<Concert> concerts = new ArrayList<>();
        LongStream.range(0, 3).forEach(idx -> {
            Concert concert = Concert.builder()
                    .id(idx)
                    .name("아이유 데뷔 20주년 콘서트")
                    .startedAt(now)
                    .endedAt(now.plusHours(idx))
                    .description("아이유 데뷔 20주년을 기념하는 콘서트")
                    .createdAt(now.plusHours(idx))
                    .modifiedAt(now)
                    .fundingAmount(1000 + idx)
                    .targetAmount(10000L)
                    .artist(Artist.builder().build())
                    .build();
            concertPersistenceAdapter.save(concert);
            concerts.add(concert);
        });
        return concerts;
    }
}
