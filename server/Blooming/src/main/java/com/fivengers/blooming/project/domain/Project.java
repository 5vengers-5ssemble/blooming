package com.fivengers.blooming.project.domain;

import com.fivengers.blooming.artist.domain.Artist;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

    private Long id;
    private String name;
    private Long fundingAmount;
    private Long targetAmount;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Artist artist;

    public Project(Long id, String name, Long fundingAmount, Long targetAmount,
            LocalDateTime startedAt, LocalDateTime endedAt, String description,
            LocalDateTime createdAt, LocalDateTime modifiedAt, Artist artist) {
        this.id = id;
        this.name = name;
        this.fundingAmount = fundingAmount;
        this.targetAmount = targetAmount;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.description = description;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.artist = artist;
    }
}
