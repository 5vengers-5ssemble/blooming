package com.fivengers.blooming.artistscrap.application;

import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artistscrap.application.port.in.ArtistScrapRecordUseCase;
import com.fivengers.blooming.artistscrap.application.port.out.ArtistScrapPort;
import com.fivengers.blooming.artistscrap.application.port.out.ArtistScrapRecordPort;
import com.fivengers.blooming.artistscrap.domain.ArtistScrapRecord;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArtistScrapRecordService implements ArtistScrapRecordUseCase {

    private final ArtistScrapRecordPort artistScrapRecordPort;
    private final ArtistScrapPort artistScrapPort;
    private final ArtistPort artistPort;
    public static final long LIMIT_WEEK = 4L;

    @Override
    public List<ArtistScrapRecord> findOnLatestFourWeek(Long artistId) {
        return artistScrapRecordPort.findTopByArtistIdOrderByStartDateDesc(artistId, LIMIT_WEEK);
    }

    @Scheduled(cron = "59 59 23 * * 0")
    @Transactional
    public void recordEverySunday() {
        LocalDateTime start = getStartOfWeekDateTime(DayOfWeek.MONDAY);
        LocalDateTime end = getEndOfWeekDateTime(DayOfWeek.SUNDAY);

        artistPort.findAll()
                .forEach(artist -> {
                    artistScrapPort.countByArtistId(artist.getId());
                    artistScrapRecordPort.save(ArtistScrapRecord.builder()
                            .scrapCount(1)
                            .startDateOnWeek(start)
                            .endDateOnWeek(end)
                            .artist(artist)
                            .build());
                });
    }

    @Transactional
    public void recordIfOnWeek(Artist artist, ChangeCountConsumer<ArtistScrapRecord> consumer) {
        LocalDateTime start = getStartOfWeekDateTime(DayOfWeek.MONDAY);
        LocalDateTime end = getEndOfWeekDateTime(DayOfWeek.SUNDAY);

        if (!isWithinThisWeek(LocalDateTime.now(), start, end)) {
            return;
        }

        artistScrapRecordPort.findOnWeek(start, end, artist.getId()).ifPresentOrElse(
                record -> {
                    consumer.changeCount(record);
                    artistScrapRecordPort.update(record);
                },
                () -> artistScrapRecordPort.save(ArtistScrapRecord.builder()
                        .scrapCount(1)
                        .startDateOnWeek(start)
                        .endDateOnWeek(end)
                        .artist(artist)
                        .build()));
    }

    private boolean isWithinThisWeek(LocalDateTime now, LocalDateTime start, LocalDateTime end) {
        return now.isAfter(start) && now.isBefore(end);
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
