package com.fivengers.blooming.membership.application.port.in;

import com.fivengers.blooming.membership.domain.MembershipRecord;
import java.util.List;

public interface MembershipRecordUseCase {

    List<MembershipRecord> findOnLatestFourWeek(Long membershipId);
}
