package com.fivengers.blooming.artist.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Artist {

    private Long id;
    private String stageName;
    private String agency;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public Artist(Long id,
                  String stageName,
                  String agency,
                  String description,
                  LocalDateTime createdAt,
                  LocalDateTime modifiedAt) {
        this.id = id;
        this.stageName = stageName;
        this.agency = agency;
        this.description = description;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static Artist from(String stageName, String agency, String description) {
        return Artist.builder()
                .stageName(stageName)
                .agency(agency)
                .description(description)
                .build();
    }
}
