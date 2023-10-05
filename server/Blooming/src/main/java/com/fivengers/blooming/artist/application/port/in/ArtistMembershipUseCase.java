package com.fivengers.blooming.artist.application.port.in;

import com.fivengers.blooming.membership.domain.Membership;

public interface ArtistMembershipUseCase {
    Membership searchOngoingByArtistId(Long artistId);
}
