package com.fivengers.blooming.project.adapter.out.persistence.entity;


import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "activity")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ActivityJpaEntity extends ProjectJpaEntity {

    @Column
    private String albumImgUrl;
    @Column
    private String tracklistImgUrl;
    @Column
    private String compositionImgUrl;

    @Builder
    public ActivityJpaEntity(Long id,
            String name,
            Long fundingAmount,
            Long targetAmount,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String introduction,
            String description,
            String teaserVideoUrl,
            Integer revenuePercent,
            Boolean deleted,
            ArtistJpaEntity artist,
            String albumImgUrl,
            String tracklistImgUrl,
            String compositionImgUrl) {
        super(id, name, fundingAmount, targetAmount, startedAt, endedAt,
                introduction, description, teaserVideoUrl, revenuePercent, deleted, artist);
        this.albumImgUrl = albumImgUrl;
        this.tracklistImgUrl = tracklistImgUrl;
        this.compositionImgUrl = compositionImgUrl;
    }
}
