package com.fivengers.blooming.membership.application.port.out;

import com.fivengers.blooming.membership.domain.MembershipApplication;
import com.fivengers.blooming.membership.domain.MembershipApplicationState;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MembershipApplicationPort {

    Page<MembershipApplication> findByApplicationState(Pageable pageable,
            MembershipApplicationState applicationState);
    MembershipApplication save(MembershipApplication membershipApplication);
    Optional<MembershipApplication> findById(Long applicationId);
    Optional<MembershipApplication> findByMemberId(Long memberId);
    MembershipApplication update(MembershipApplication membershipApplication);
}
