package com.fivengers.blooming.membership.application.port.in;

import com.fivengers.blooming.membership.application.port.in.dto.MembershipApplicationModifyRequest;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipApplyRequest;
import com.fivengers.blooming.membership.domain.MembershipApplication;
import com.fivengers.blooming.membership.domain.MembershipApplicationState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MembershipApplicationUseCase {

    MembershipApplication add(MembershipApplyRequest request, Long memberId);

    MembershipApplication searchByMemberId(Long memberId);

    Page<MembershipApplication> searchAll(Pageable pageable,
            MembershipApplicationState applicationState);

    MembershipApplication modifyStateById(MembershipApplicationModifyRequest request,
            Long applicationId);
}
