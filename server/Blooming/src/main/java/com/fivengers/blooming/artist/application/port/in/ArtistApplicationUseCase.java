package com.fivengers.blooming.artist.application.port.in;

import com.fivengers.blooming.artist.application.port.in.dto.ArtistApplicationStateModifyRequest;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistApplyRequest;
import com.fivengers.blooming.artist.domain.ArtistApplication;
import com.fivengers.blooming.artist.domain.ArtistApplicationState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArtistApplicationUseCase {

    ArtistApplication add(ArtistApplyRequest request, Long memberId);
    Page<ArtistApplication> searchByArtistApplicationState(Pageable pageable,
            ArtistApplicationState state);
    ArtistApplication searchById(Long applicationId);
    ArtistApplication searchByMemberId(Long memberId);
    ArtistApplication modifyStateById(Long applicationId,
            ArtistApplicationStateModifyRequest request);
}
