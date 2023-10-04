package com.fivengers.blooming.membership.application.port.in.dto;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.membership.domain.MembershipApplication;
import com.fivengers.blooming.membership.domain.MembershipApplicationState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record MembershipApplyRequest(@NotBlank String title,
                                     @NotNull String description,
                                     @NotNull LocalDateTime seasonStart,
                                     @NotNull LocalDateTime seasonEnd,
                                     @NotNull LocalDateTime purchaseStart,
                                     @NotNull LocalDateTime purchaseEnd,
                                     @NotNull String thumbnailUrl) {

    public MembershipApplication toDomain(Artist artist) {
        return MembershipApplication.builder()
                .title(title)
                .description(description)
                .seasonStart(seasonStart)
                .seasonEnd(seasonEnd)
                .purchaseStart(purchaseStart)
                .purchaseEnd(purchaseEnd)
                .thumbnailUrl(thumbnailUrl)
                .applicationState(MembershipApplicationState.APPLY)
                .artist(artist)
                .build();
    }

}
