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
@Table(name = "concert")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConcertJpaEntity extends ProjectJpaEntity {

    @Column
    private String posterImgUrl;
    @Column
    private String setlistImgUrl;
    @Column
    private String goodsImgUrl;

    @Builder
    public ConcertJpaEntity(Long id,
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
            String posterImgUrl,
            String setlistImgUrl,
            String goodsImgUrl) {
        super(id, name, fundingAmount, targetAmount, startedAt, endedAt,
                introduction, description, teaserVideoUrl, revenuePercent, deleted, artist);
        this.posterImgUrl = posterImgUrl;
        this.setlistImgUrl = setlistImgUrl;
        this.goodsImgUrl = goodsImgUrl;
    }
}
