package com.fivengers.blooming.artist.adapter.in.web.dto;

import com.fivengers.blooming.artist.domain.ArtistApplication;
import com.fivengers.blooming.artist.domain.ArtistApplicationState;
import com.fivengers.blooming.member.adapter.in.web.dto.MemberResponse;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ArtistApplicationDetailsResponse(String stageName,
                                               String agency,
                                               String description,
                                               ArtistApplicationState applicationState,
                                               LocalDateTime createdAt,
                                               LocalDateTime modifiedAt,
                                               String profileImageUrl,
                                               String youtubeUrl,
                                               String fanCafeUrl,
                                               String snsUrl,
                                               MemberResponse member) {

    public static ArtistApplicationDetailsResponse from(ArtistApplication artistApplication,
                                                        MemberResponse memberResponse) {
        return ArtistApplicationDetailsResponse.builder()
                .stageName(artistApplication.getStageName())
                .agency(artistApplication.getAgency())
                .description(artistApplication.getDescription())
                .applicationState(artistApplication.getApplicationState())
                .createdAt(artistApplication.getCreatedAt())
                .modifiedAt(artistApplication.getModifiedAt())
                .profileImageUrl(artistApplication.getProfileImageUrl())
                .youtubeUrl(artistApplication.getYoutubeUrl())
                .fanCafeUrl(artistApplication.getFanCafeUrl())
                .snsUrl(artistApplication.getSnsUrl())
                .member(memberResponse)
                .build();
    }

}
