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
    private String introduction;
    private String description;
    private String teaserVideoUrl;
    private Integer revenuePercent;
    private String profileImg;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private ProjectType dtype;

    private Artist artist;

    public Project(Long id,
            String name,
            Long fundingAmount,
            Long targetAmount,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String introduction,
            String description,
            String teaserVideoUrl,
            Integer revenuePercent,
            String profileImg,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            ProjectType dtype,
            Artist artist) {
        this.id = id;
        this.name = name;
        this.fundingAmount = fundingAmount;
        this.targetAmount = targetAmount;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.introduction = introduction;
        this.description = description;
        this.teaserVideoUrl = teaserVideoUrl;
        this.revenuePercent = revenuePercent;
        this.profileImg = profileImg;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.dtype = dtype;
        this.artist = artist;
    }
}
