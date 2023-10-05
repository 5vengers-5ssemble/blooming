package com.fivengers.blooming.membership.application.port;

import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.global.exception.artist.ArtistNotFoundException;
import com.fivengers.blooming.global.exception.membership.MembershipApplicationNotFoundException;
import com.fivengers.blooming.membership.application.port.in.MembershipApplicationUseCase;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipApplicationModifyRequest;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipApplyRequest;
import com.fivengers.blooming.membership.application.port.out.MembershipApplicationPort;
import com.fivengers.blooming.membership.domain.MembershipApplication;
import com.fivengers.blooming.membership.domain.MembershipApplicationState;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipApplicationService implements MembershipApplicationUseCase {

    private final MembershipApplicationPort membershipApplicationPort;
    private final ArtistPort artistPort;

    @Override
    public MembershipApplication add(MembershipApplyRequest request, Long memberId) {
        return membershipApplicationPort.save(request.toDomain(artistPort
                .findByMemberId(memberId)
                .orElseThrow(ArtistNotFoundException::new)));
    }

    @Override
    public MembershipApplication searchByMemberIdAndApplicationState(Long memberId,
            MembershipApplicationState applicationState) {
        return membershipApplicationPort
                .findByMemberIdAndApplicationState(memberId, applicationState)
                .orElseThrow(MembershipApplicationNotFoundException::new);
    }

    @Override
    public Page<MembershipApplication> searchAll(Pageable pageable,
            MembershipApplicationState applicationState) {
        return membershipApplicationPort.findByApplicationState(pageable, applicationState);
    }

    @Override
    public MembershipApplication modifyStateById(MembershipApplicationModifyRequest request,
            Long applicationId) {
        MembershipApplication application = membershipApplicationPort.findById(
                        applicationId)
                .orElseThrow(MembershipApplicationNotFoundException::new);

        application.changeState(request.applicationState());
        return membershipApplicationPort.update(application);
    }
}
