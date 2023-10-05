package com.fivengers.blooming.membership.application.port.in;

import com.fivengers.blooming.membership.application.port.in.dto.MembershipCreateRequest;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipModifyRequest;
import com.fivengers.blooming.membership.domain.Membership;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MembershipUseCase {

    Membership add(MembershipCreateRequest request);
    Page<Membership> searchLatestSeasons(Pageable pageable);
    Page<Membership> searchOngoing(Pageable pageable);
    List<Membership> searchTop3SalesMembership();
    Page<Membership> searchByArtistNameContains(Pageable pageable, String searchQuery);
    Membership modify(MembershipModifyRequest request, Long membershipId, Long memberId);
    Membership searchByMembershipId(Long membershipId);

}
