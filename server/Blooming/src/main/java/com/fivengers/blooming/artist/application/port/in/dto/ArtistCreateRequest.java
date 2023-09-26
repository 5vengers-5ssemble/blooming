package com.fivengers.blooming.artist.application.port.in.dto;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

public record ArtistCreateRequest(@NotBlank String stageName,
                                  @NotBlank String agency,
                                  @NotNull String description,
                                  @NotNull String profileImageUrl,
                                  @NotNull String youtubeUrl,
                                  @NotNull String fanCafeUrl,
                                  @NotNull String snsUrl,
                                  @Validated ArtistVideoCreateRequest artistVideo) {

    public Artist toDomain(Member member) {
        return Artist.builder()
                .stageName(stageName)
                .agency(agency)
                .description(description)
                .profileImageUrl(profileImageUrl)
                .youtubeUrl(youtubeUrl)
                .fanCafeUrl(fanCafeUrl)
                .snsUrl(snsUrl)
                .member(member)
                .build();
    }
}
