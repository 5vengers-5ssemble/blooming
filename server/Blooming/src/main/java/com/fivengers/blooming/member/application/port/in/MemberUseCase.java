package com.fivengers.blooming.member.application.port.in;

import com.fivengers.blooming.member.application.port.in.dto.MemberModifyRequest;
import com.fivengers.blooming.member.domain.Member;

public interface MemberUseCase {

    Member findById(Long memberId);
    Member modify(MemberModifyRequest request, Long requestMemberId, Long loginMemberId);
}
