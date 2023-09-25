package com.fivengers.blooming.artistscrap.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artistscrap.application.port.out.ArtistScrapPort;
import com.fivengers.blooming.artistscrap.application.port.out.ArtistScrapRecordPort;
import com.fivengers.blooming.artistscrap.domain.ArtistScrapRecord;
import com.fivengers.blooming.fixture.artist.adapter.out.persistence.FakeArtistPersistenceAdapter;
import com.fivengers.blooming.fixture.artistscrap.adapter.out.persistence.FakeArtistScrapPersistenceAdapter;
import com.fivengers.blooming.fixture.artistscrap.adapter.out.persistence.FakeArtistScrapRecordPersistenceAdapter;
import com.fivengers.blooming.fixture.member.adapter.out.persistence.FakeMemberPersistenceAdapter;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberRole;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArtistScrapRecordServiceTest {
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
        LocalDateTime now = LocalDateTime.now();
        this.member = Member.builder()
                .id(1L)
                .oauthProvider(AuthProvider.KAKAO)
                .oauthAccount("12434512")
                .name("이지은")
                .nickname("아이유")
                .account("account")
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
    @DisplayName("최근 4주에 기록된 관심도 정보를 가져온다.")
    void findOnLatest4Week() {
        IntStream.range(0, 5)
                .mapToObj(i -> ArtistScrapRecord.builder()
                        .scrapCount(2)
                        .startDateOnWeek(getThisWeekDateTime(Calendar.SUNDAY, i, 0, 0, 0, 0))
                        .endDateOnWeek(
                                getThisWeekDateTime(Calendar.SATURDAY, i, 23, 59, 59,
                                        999_999_999))
                        .artist(artist)
                        .build())
                .forEach(record -> artistScrapRecordPort.save(record));

        List<ArtistScrapRecord> records = artistScrapRecordService
                .findOnLatestFourWeek(artist.getId());

        assertThat(records).hasSize(4);
        assertThat(records.get(0).getStartDateOnWeek()).isEqualTo(
                getThisWeekDateTime(Calendar.SUNDAY, 0, 0, 0, 0, 0));
    }

    @Test
    @DisplayName("관심 등록 날짜가 이번주라면 기록한다.")
    void upCountRecordIfOnWeek() {
        artistScrapRecordService.recordIfOnWeek(artist, ArtistScrapRecord::upCount);

        Optional<ArtistScrapRecord> artistScrapRecord = artistScrapRecordPort.findOnWeek(
                getThisWeekDateTime(Calendar.SUNDAY, 0, 0, 0, 0, 0),
                getThisWeekDateTime(Calendar.SATURDAY, 0, 23, 59, 59, 999_999_999),
                artist);

        assertThat(artistScrapRecord).isNotEmpty();
        assertThat(artistScrapRecord.get().getScrapCount()).isEqualTo(1L);
    }

    @Test
    @DisplayName("관심 취소 날짜가 이번주라면 기록한다.")
    void downCountRecordIfOnWeek() {
        artistScrapRecordService.recordIfOnWeek(artist, ArtistScrapRecord::upCount);
        artistScrapRecordService.recordIfOnWeek(artist, ArtistScrapRecord::upCount);
        artistScrapRecordService.recordIfOnWeek(artist, ArtistScrapRecord::upCount);
        artistScrapRecordService.recordIfOnWeek(artist, ArtistScrapRecord::downCount);

        Optional<ArtistScrapRecord> artistScrapRecord = artistScrapRecordPort.findOnWeek(
                getThisWeekDateTime(Calendar.SUNDAY, 0, 0, 0, 0, 0),
                getThisWeekDateTime(Calendar.SATURDAY, 0, 23, 59, 59, 999_999_999),
                artist);

        assertThat(artistScrapRecord).isNotEmpty();
        assertThat(artistScrapRecord.get().getScrapCount()).isEqualTo(2L);
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