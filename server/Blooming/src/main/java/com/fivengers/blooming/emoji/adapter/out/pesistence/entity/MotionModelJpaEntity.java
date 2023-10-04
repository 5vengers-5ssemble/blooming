package com.fivengers.blooming.emoji.adapter.out.pesistence.entity;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.global.audit.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "motion_model")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MotionModelJpaEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "motion_model_id")
    private Long id;

    @Column(nullable = false)
    private String modelUrl;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = true)
    private ArtistJpaEntity artistJpaEntity;

    @Builder
    public MotionModelJpaEntity(Long id, String modelUrl, Boolean deleted,
            ArtistJpaEntity artistJpaEntity) {
        this.id = id;
        this.modelUrl = modelUrl;
        this.deleted = deleted;
        this.artistJpaEntity = artistJpaEntity;
    }
}
