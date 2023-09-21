package com.fivengers.blooming.project.domain;

import com.fivengers.blooming.artist.domain.Artist;
import jakarta.persistence.Column;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Concert extends Project {

    private String posterImgUrl;
    private String setlistImgUrl;
    private String goodsImgUrl;

    @Builder
    public Concert(Long id,
            String name,
            Long fundingAmount,
            Long targetAmount,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String introduction,
            String description,
            String teaserVideoUrl,
            Integer revenuePercent,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            Artist artist,
            String posterImgUrl,
            String setlistImgUrl,
            String goodsImgUrl) {
        super(id, name, fundingAmount, targetAmount, startedAt, endedAt,
                introduction, description, teaserVideoUrl, revenuePercent,
                createdAt, modifiedAt, artist);
        this.posterImgUrl = posterImgUrl;
        this.setlistImgUrl = setlistImgUrl;
        this.goodsImgUrl = goodsImgUrl;
    }
}
