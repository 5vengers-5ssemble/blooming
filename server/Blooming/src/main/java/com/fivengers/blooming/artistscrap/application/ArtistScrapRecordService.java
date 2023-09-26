package com.fivengers.blooming.artistscrap.application;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artistscrap.application.port.in.ArtistScrapRecordUseCase;
import com.fivengers.blooming.artistscrap.application.port.out.ArtistScrapRecordPort;
import com.fivengers.blooming.artistscrap.domain.ArtistScrapRecord;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistScrapRecordService implements ArtistScrapRecordUseCase {

    private final ArtistScrapRecordPort artistScrapRecordPort;
    public static final long LIMIT_WEEK = 4L;

    public List<ArtistScrapRecord> findOnLatestFourWeek(Long artistId) {
        return artistScrapRecordPort.findTopByArtistIdOrderByStartDateDesc(artistId, LIMIT_WEEK);
    }

    public void recordIfOnWeek(Artist artist, ChangeCountConsumer<ArtistScrapRecord> consumer) {
        LocalDateTime start = getStartOfWeekDateTime();
        LocalDateTime end = getEndOfWeekDateTime();

        if (!isWithinThisWeek(LocalDateTime.now(), start, end)) {
            return;
        }

        artistScrapRecordPort.findOnWeek(start, end, artist).ifPresentOrElse(
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

    private LocalDateTime getThisWeekDateTime(int dayOfWeek, int prevWeek, int hour, int minute, int second,
            int nanoOfSecond) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        calendar.add(Calendar.DATE, 7);
        return LocalDateTime.ofInstant(calendar.getTime().toInstant(),
                        calendar.getTimeZone().toZoneId())
                .minusWeeks(1 + prevWeek) // minusWeek(1): this week
                .toLocalDate().atTime(hour, minute, second, nanoOfSecond);
    }

    private LocalDateTime getStartOfWeekDateTime() {
        return getThisWeekDateTime(Calendar.MONDAY, 0,0, 0, 0, 0);
    }

    private LocalDateTime getEndOfWeekDateTime() {
        return getThisWeekDateTime(Calendar.SUNDAY, -1,23, 59, 59, 999_999_999);
    }
}
