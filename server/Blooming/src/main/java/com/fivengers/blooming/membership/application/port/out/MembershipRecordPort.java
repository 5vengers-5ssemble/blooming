package com.fivengers.blooming.membership.application.port.out;

import com.fivengers.blooming.membership.domain.MembershipRecord;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MembershipRecordPort {

    MembershipRecord save(MembershipRecord membershipRecord);
    List<MembershipRecord> findTopByMembershipIdOrderByStartDateDesc(Long membershipId, Long limit);
    Optional<MembershipRecord> findOnWeek(LocalDateTime startDate, LocalDateTime endDate,
            Long membershipId);
    void update(MembershipRecord membershipRecord);

}
