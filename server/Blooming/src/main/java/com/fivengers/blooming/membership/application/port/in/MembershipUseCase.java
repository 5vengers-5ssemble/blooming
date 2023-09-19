package com.fivengers.blooming.membership.application.port.in;

import com.fivengers.blooming.membership.domain.Membership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MembershipUseCase {

    Page<Membership> searchLatestSeasons(Pageable pageable);
}
