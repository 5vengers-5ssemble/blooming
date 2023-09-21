package com.fivengers.blooming.membership.application.port.in;

import com.fivengers.blooming.membership.application.port.in.dto.MembershipCreateRequest;
import com.fivengers.blooming.membership.domain.Membership;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MembershipUseCase {

    Membership add(MembershipCreateRequest request);
    Page<Membership> searchLatestSeasons(Pageable pageable);
}
