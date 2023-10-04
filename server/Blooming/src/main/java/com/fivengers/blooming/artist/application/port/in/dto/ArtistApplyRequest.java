package com.fivengers.blooming.artist.application.port.in.dto;

import com.fivengers.blooming.artist.domain.ArtistApplication;
import com.fivengers.blooming.artist.domain.ArtistApplicationState;
import com.fivengers.blooming.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ArtistApplyRequest(@NotBlank String stageName,
                                 @NotBlank String agency,
                                 @NotNull String description,
                                 @NotNull String profileImageUrl,
                                 @NotNull String youtubeUrl,
                                 @NotNull String fanCafeUrl,
                                 @NotNull String snsUrl) {

    public ArtistApplication toDomain(Member member) {
        return ArtistApplication.builder()
                .stageName(stageName)
                .agency(agency)
                .description(description)
                .applicationState(ArtistApplicationState.APPLY)
                .profileImageUrl(profileImageUrl)
                .youtubeUrl(youtubeUrl)
                .fanCafeUrl(fanCafeUrl)
                .snsUrl(snsUrl)
                .member(member)
                .build();
    }
}
