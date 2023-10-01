package com.fivengers.blooming.artist.domain;

import com.fivengers.blooming.member.domain.Member;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ArtistApplication {

    private Long id;
    private String stageName;
    private String agency;
    private String description;
    private ArtistApplicationState applicationState;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String profileImageUrl;
    private String youtubeUrl;
    private String fanCafeUrl;
    private String snsUrl;
    private Member member;

    @Builder
    public ArtistApplication(Long id, String stageName, String agency, String description,
            ArtistApplicationState applicationState, LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            String profileImageUrl, String youtubeUrl, String fanCafeUrl, String snsUrl,
            Member member) {
        this.id = id;
        this.stageName = stageName;
        this.agency = agency;
        this.description = description;
        this.applicationState = applicationState;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.profileImageUrl = profileImageUrl;
        this.youtubeUrl = youtubeUrl;
        this.fanCafeUrl = fanCafeUrl;
        this.snsUrl = snsUrl;
        this.member = member;
    }

    public void changeState(ArtistApplicationState state) {
        this.applicationState = state;
    }
}
