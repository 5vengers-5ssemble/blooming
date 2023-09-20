package com.fivengers.blooming.artist.domain;

import com.fivengers.blooming.member.domain.Member;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Artist {

    private Long id;
    private String stageName;
    private String agency;
    private String description;
    private String profileImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Member member;

    @Builder
    public Artist(Long id,
                  String stageName,
                  String agency,
                  String description,
                  String profileImageUrl,
                  LocalDateTime createdAt,
                  LocalDateTime modifiedAt,
                  Member member) {
        this.id = id;
        this.stageName = stageName;
        this.agency = agency;
        this.description = description;
        this.profileImageUrl = profileImageUrl;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.member = member;
    }

    public static Artist from(String stageName, String agency, String description) {
        return Artist.builder()
                .stageName(stageName)
                .agency(agency)
                .description(description)
                .build();
    }
}
