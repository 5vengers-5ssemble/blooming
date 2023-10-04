package com.fivengers.blooming.membership.domain;

import com.fivengers.blooming.artist.domain.Artist;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MembershipApplication {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime seasonStart;
    private LocalDateTime seasonEnd;
    private LocalDateTime purchaseStart;
    private LocalDateTime purchaseEnd;
    private String thumbnailUrl;
    private MembershipApplicationState applicationState;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Artist artist;

    @Builder
    public MembershipApplication(Long id,
                                 String title,
                                 String description,
                                 LocalDateTime seasonStart,
                                 LocalDateTime seasonEnd,
                                 LocalDateTime purchaseStart,
                                 LocalDateTime purchaseEnd,
                                 String thumbnailUrl,
                                 MembershipApplicationState applicationState,
                                 LocalDateTime createdAt,
                                 LocalDateTime modifiedAt,
                                 Artist artist) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.seasonStart = seasonStart;
        this.seasonEnd = seasonEnd;
        this.purchaseStart = purchaseStart;
        this.purchaseEnd = purchaseEnd;
        this.thumbnailUrl = thumbnailUrl;
        this.applicationState = applicationState;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.artist = artist;
    }

    public boolean isApplier(Long memberId) {
        return this.artist.getMember().getId().equals(memberId);
    }

    public void changeState(MembershipApplicationState applicationState) {
        this.applicationState = applicationState;
    }
}
