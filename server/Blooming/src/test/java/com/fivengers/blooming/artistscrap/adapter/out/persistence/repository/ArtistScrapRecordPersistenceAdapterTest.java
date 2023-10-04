package com.fivengers.blooming.artistscrap.adapter.out.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.artist.adapter.out.persistence.repository.ArtistPersistenceAdapter;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artistscrap.adapter.out.persistence.entity.ArtistScrapRecordJpaEntity;
import com.fivengers.blooming.artistscrap.adapter.out.persistence.mapper.ArtistScrapRecordMapper;
import com.fivengers.blooming.artistscrap.domain.ArtistScrapRecord;
import com.fivengers.blooming.member.adapter.out.persistence.repository.MemberPersistenceAdapter;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberRole;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
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

    @Autowired MemberPersistenceAdapter memberPersistenceAdapter;
    @Autowired ArtistScrapRecordPersistenceAdapter artistScrapRecordPersistenceAdapter;
    @Autowired ArtistScrapRecordSpringDataRepository artistScrapRecordSpringDataRepository;
    @Autowired ArtistPersistenceAdapter artistPersistenceAdapter;
    @Autowired ArtistScrapRecordMapper artistScrapRecordMapper;
    @Autowired ArtistMapper artistMapper;
    Member member;
    Artist artist;

    @BeforeEach
    void initObjects() {
        LocalDateTime now = LocalDateTime.now();
        member = memberPersistenceAdapter.save(Member.builder()
                .oauthProvider(AuthProvider.KAKAO)
                .oauthAccount("12434512")
                .name("이지은")
                .nickname("아이유")
                .account("account")
                .createdAt(now)
                .modifiedAt(now)
                .role(List.of(MemberRole.ROLE_USER))
                .build());
        artist = artistPersistenceAdapter.save(Artist.builder()
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
                .build());
    }

    @Test
    @DisplayName("아티스트 ID를 통해 최근 시작 일자로 정렬하여 제한된 개수의 기록 목록을 가져온다.")
    void findByArtistOrderByStartDateLimit() {
        IntStream.range(0, 5)
                .mapToObj(i -> ArtistScrapRecord.builder()
                        .scrapCount(2)
                        .startDateOnWeek(getStartOfWeekDateTime(DayOfWeek.MONDAY))
                        .endDateOnWeek(getEndOfWeekDateTime(DayOfWeek.SUNDAY))
                        .artist(artist)
                        .build())
                .forEach(record -> artistScrapRecordPersistenceAdapter.save(record));

        List<ArtistScrapRecord> records = artistScrapRecordPersistenceAdapter
                .findTopByArtistIdOrderByStartDateDesc(artist.getId(), 4L);

        assertThat(records).hasSize(4);
        assertThat(records.get(0).getStartDateOnWeek())
                .isEqualTo(getStartOfWeekDateTime(DayOfWeek.MONDAY));

    }

    @Test
    @DisplayName("아티스트 스크랩 기록을 저장한다.")
    void saveArtistScrapRecord() {
        ArtistScrapRecord artistScrapRecord = artistScrapRecordPersistenceAdapter.save(
                ArtistScrapRecord.builder()
                        .scrapCount(1)
                        .startDateOnWeek(getStartOfWeekDateTime(DayOfWeek.MONDAY))
                        .endDateOnWeek(getEndOfWeekDateTime(DayOfWeek.SUNDAY))
                        .artist(artist)
                        .build());

        Optional<ArtistScrapRecordJpaEntity> findRecord = artistScrapRecordSpringDataRepository.findById(
                artistScrapRecord.getId());
        assertThat(findRecord).isNotEmpty();
        assertThat(findRecord.get().getId()).isEqualTo(artistScrapRecord.getId());
    }

    private LocalDateTime getStartOfWeekDateTime(DayOfWeek dayOfWeek) {
        return LocalDate.now().atTime(0, 0, 0, 0)
                .with(TemporalAdjusters.previousOrSame(dayOfWeek));
    }

    private LocalDateTime getEndOfWeekDateTime(DayOfWeek dayOfWeek) {
        return LocalDate.now().atTime(23, 59, 59, 999_999_999)
                .with(TemporalAdjusters.nextOrSame(dayOfWeek));
    }
}