package com.fivengers.blooming.artist.adapter.in.web.dto;

import com.fivengers.blooming.artist.domain.ArtistApplication;
import com.fivengers.blooming.artist.domain.ArtistApplicationState;
import com.fivengers.blooming.member.adapter.in.web.dto.MemberResponse;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ArtistApplicationListResponse(Long id,
                                            String stageName,
                                            String agency,
                                            ArtistApplicationState applicationState,
                                            LocalDateTime createdAt,
                                            LocalDateTime modifiedAt,
                                            MemberResponse member) {

    public static ArtistApplicationListResponse from(ArtistApplication artistApplication,
            MemberResponse memberResponse) {
        return ArtistApplicationListResponse.builder()
                .id(artistApplication.getId())
                .stageName(artistApplication.getStageName())
                .agency(artistApplication.getAgency())
                .applicationState(artistApplication.getApplicationState())
                .createdAt(artistApplication.getCreatedAt())
                .modifiedAt(artistApplication.getModifiedAt())
                .member(memberResponse)
                .build();
    }

}
