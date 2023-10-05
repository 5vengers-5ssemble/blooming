package com.fivengers.blooming.membership.application;

import com.fivengers.blooming.artistscrap.application.ChangeCountConsumer;
import com.fivengers.blooming.membership.application.port.in.MembershipRecordUseCase;
import com.fivengers.blooming.membership.application.port.out.MembershipRecordPort;
import com.fivengers.blooming.membership.domain.Membership;
import com.fivengers.blooming.membership.domain.MembershipRecord;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MembershipRecordService implements MembershipRecordUseCase {

    private final MembershipRecordPort membershipRecordPort;
    private static final long LIMIT_WEEK = 4L;

    @Override
    public List<MembershipRecord> findOnLatestFourWeek(Long membershipId) {
        return membershipRecordPort
                .findTopByMembershipIdOrderByStartDateDesc(membershipId, LIMIT_WEEK);
    }

    @Transactional
    public void recordIfOnWeek(Membership membership, ChangeCountConsumer<MembershipRecord> consumer) {
        LocalDateTime start = getStartOfWeekDateTime(DayOfWeek.MONDAY);
        LocalDateTime end = getEndOfWeekDateTime(DayOfWeek.SUNDAY);

        if (!isWithinThisWeek(LocalDateTime.now(), start, end)) {
            return;
        }

        membershipRecordPort.findOnWeek(start, end, membership.getId()).ifPresentOrElse(
                record -> {
                    consumer.changeCount(record);
                    membershipRecordPort.update(record);
                },
                () -> membershipRecordPort.save(MembershipRecord.builder()
                        .saleCount(1)
                        .startDateOnWeek(start)
                        .endDateOnWeek(end)
                        .membership(membership)
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
