package com.fivengers.blooming.membership.application;

import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.global.exception.artist.ArtistNotFoundException;
import com.fivengers.blooming.global.exception.membership.InvalidMembershipModifyRequestException;
import com.fivengers.blooming.global.exception.membership.MembershipNotFoundException;
import com.fivengers.blooming.membership.application.port.in.MembershipUseCase;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipCreateRequest;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipModifyRequest;
import com.fivengers.blooming.membership.application.port.out.MembershipPort;
import com.fivengers.blooming.membership.domain.Membership;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService implements MembershipUseCase {

    private final MembershipPort membershipPort;
    private final ArtistPort artistPort;

    @Override
    public Membership add(MembershipCreateRequest request) {
        return membershipPort.save(request.toDomain(artistPort.findById(request.artistId())
                .orElseThrow(ArtistNotFoundException::new)));
    }

    @Override
    public Page<Membership> searchLatestSeasons(Pageable pageable) {
        return membershipPort.findLatestSeasons(pageable);
    }

    @Override
    public List<Membership> searchTop3SalesMembership() {
        return membershipPort.findByTopNSalesCount(3L);
    }

    @Override
    public Membership modify(MembershipModifyRequest request, Long membershipId, Long memberId) {
        Membership membership = membershipPort.findById(membershipId)
                .orElseThrow(MembershipNotFoundException::new);

        if (membership.isOwner(memberId)) {
            membership.update(request.title(),
                    request.description(),
                    request.seasonStart(),
                    request.seasonEnd(),
                    request.purchaseStart(),
                    request.purchaseEnd(),
                    request.thumbnailUrl());

            return membershipPort.update(membership);
        }

        throw new InvalidMembershipModifyRequestException();
    }
}
