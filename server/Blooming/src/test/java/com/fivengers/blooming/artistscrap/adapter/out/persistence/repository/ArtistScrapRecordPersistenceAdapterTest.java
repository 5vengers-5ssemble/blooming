package com.fivengers.blooming.artistscrap.adapter.out.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.artist.adapter.out.persistence.repository.ArtistPersistenceAdapter;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artistscrap.adapter.out.persistence.entity.ArtistScrapRecordJpaEntity;
import com.fivengers.blooming.artistscrap.adapter.out.persistence.mapper.ArtistScrapRecordMapper;
import com.fivengers.blooming.artistscrap.domain.ArtistScrapRecord;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArtistScrapRecordPersistenceAdapterTest {

    @Autowired ArtistScrapRecordPersistenceAdapter artistScrapRecordPersistenceAdapter;
    @Autowired ArtistScrapRecordSpringDataRepository artistScrapRecordSpringDataRepository;
    @Autowired ArtistPersistenceAdapter artistPersistenceAdapter;
    @Autowired ArtistScrapRecordMapper artistScrapRecordMapper;
    @Autowired ArtistMapper artistMapper;
    Artist artist;

    @BeforeEach
    void initObjects() {
        LocalDateTime now = LocalDateTime.now();
        artist = artistPersistenceAdapter.save(Artist.builder()
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
                .build());
    }

    @Test
    @DisplayName("아티스트 ID를 통해 최근 시작 일자로 정렬하여 제한된 개수의 기록 목록을 가져온다.")
    void findByArtistOrderByStartDateLimit() {
        IntStream.range(0, 5)
                .mapToObj(i -> ArtistScrapRecord.builder()
                        .scrapCount(2)
                        .startDateOnWeek(
                                getThisWeekDateTime(Calendar.SUNDAY, i, 0, 0, 0, 0))
                        .endDateOnWeek(
                                getThisWeekDateTime(Calendar.SATURDAY, i, 23, 59, 59,
                                        999_999_999))
                        .artist(artist)
                        .build())
                .forEach(record -> artistScrapRecordPersistenceAdapter.save(record));

        List<ArtistScrapRecord> records = artistScrapRecordPersistenceAdapter
                .findTopByArtistIdOrderByStartDateDesc(artist.getId(), 4L);

        assertThat(records).hasSize(4);
        assertThat(records.get(0).getStartDateOnWeek()).isEqualTo(
                getThisWeekDateTime(Calendar.SUNDAY, 0, 0, 0, 0, 0));

    }

    @Test
    @DisplayName("아티스트 스크랩 기록을 저장한다.")
    void saveArtistScrapRecord() {
        ArtistScrapRecord artistScrapRecord = artistScrapRecordPersistenceAdapter.save(
                ArtistScrapRecord.builder()
                        .scrapCount(1)
                        .startDateOnWeek(getThisWeekDateTime(Calendar.SUNDAY, 0, 0, 0, 0, 0))
                        .endDateOnWeek(
                                getThisWeekDateTime(Calendar.SATURDAY, 0, 23, 59, 59, 999_999_999))
                        .artist(artist)
                        .build());

        Optional<ArtistScrapRecordJpaEntity> findRecord = artistScrapRecordSpringDataRepository.findById(
                artistScrapRecord.getId());
        assertThat(findRecord).isNotEmpty();
        assertThat(findRecord.get().getId()).isEqualTo(artistScrapRecord.getId());
    }

    private LocalDateTime getThisWeekDateTime(int dayOfWeek, int prevWeek, int hour, int minute, int second,
            int nanoOfSecond) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        calendar.add(Calendar.DATE, 7);
        return LocalDateTime.ofInstant(calendar.getTime().toInstant(),
                        calendar.getTimeZone().toZoneId())
                .minusWeeks(1 + prevWeek)
                .toLocalDate().atTime(hour, minute, second, nanoOfSecond);
    }
}