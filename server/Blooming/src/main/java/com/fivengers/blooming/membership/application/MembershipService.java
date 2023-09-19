package com.fivengers.blooming.membership.application;

import com.fivengers.blooming.membership.application.port.in.MembershipUseCase;
import com.fivengers.blooming.membership.application.port.out.MembershipPort;
import com.fivengers.blooming.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService implements MembershipUseCase {

    private final MembershipPort membershipPort;

    @Override
    public Page<Membership> searchLatestSeasons(Pageable pageable) {
        return membershipPort.findLatestSeasons(pageable);
    }
}
