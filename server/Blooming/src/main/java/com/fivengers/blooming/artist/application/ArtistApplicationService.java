package com.fivengers.blooming.artist.application;

import com.fivengers.blooming.artist.application.port.in.ArtistApplicationUseCase;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistApplicationModifyRequest;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistApplyRequest;
import com.fivengers.blooming.artist.application.port.out.ArtistApplicationPort;
import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artist.domain.ArtistApplication;
import com.fivengers.blooming.artist.domain.ArtistApplicationState;
import com.fivengers.blooming.global.exception.artist.ArtistApplicationNotFoundException;
import com.fivengers.blooming.global.exception.member.MemberNotFoundException;
import com.fivengers.blooming.member.application.port.out.MemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArtistApplicationService implements ArtistApplicationUseCase {

    private final MemberPort memberPort;
    private final ArtistApplicationPort artistApplicationPort;
    private final ArtistPort artistPort;

    @Override
    public ArtistApplication add(ArtistApplyRequest request, Long memberId) {
        return artistApplicationPort.save(request.toDomain(
                memberPort.findById(memberId).orElseThrow(MemberNotFoundException::new)));
    }

    @Override
    public Page<ArtistApplication> searchByArtistApplicationState(Pageable pageable,
            ArtistApplicationState state) {
        return artistApplicationPort.findByApplicationState(pageable, state);
    }

    @Override
    public ArtistApplication searchById(Long applicationId) {
        return artistApplicationPort.findById(applicationId)
                .orElseThrow(ArtistApplicationNotFoundException::new);
    }

    @Override
    public ArtistApplication searchByMemberId(Long memberId) {
        return artistApplicationPort.findByMemberId(memberId)
                .orElseThrow(ArtistApplicationNotFoundException::new);
    }

    @Override
    @Transactional
    public ArtistApplication modifyStateById(Long applicationId,
                                             ArtistApplicationModifyRequest request) {
        ArtistApplication application = artistApplicationPort.findById(applicationId)
                .orElseThrow(ArtistApplicationNotFoundException::new);
        application.changeState(request.applicationState());

        ArtistApplication updatedApplication = artistApplicationPort.update(application);
        saveIfApproved(updatedApplication);
        return updatedApplication;
    }

    private void saveIfApproved(ArtistApplication application) {
        if (application.getApplicationState().equals(ArtistApplicationState.APPROVAL)) {
            artistPort.save(Artist.builder()
                    .stageName(application.getStageName())
                    .agency(application.getAgency())
                    .description(application.getDescription())
                    .profileImageUrl(application.getProfileImageUrl())
                    .youtubeUrl(application.getYoutubeUrl())
                    .fanCafeUrl(application.getFanCafeUrl())
                    .snsUrl(application.getSnsUrl())
                    .member(application.getMember())
                    .build());
        }
    }
}
