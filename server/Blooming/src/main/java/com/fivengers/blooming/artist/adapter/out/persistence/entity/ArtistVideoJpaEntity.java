package com.fivengers.blooming.artist.adapter.out.persistence.entity;

import com.fivengers.blooming.artist.domain.ArtistVideo;
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
import org.hibernate.annotations.Where;

@Entity
@Table(name = "artist_video")
@Getter
@Where(clause = "deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArtistVideoJpaEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_video_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String videoUrl;

    @Column(nullable = false)
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private ArtistJpaEntity artistJpaEntity;

    @Builder
    public ArtistVideoJpaEntity(Long id, String videoUrl, Boolean deleted,
            ArtistJpaEntity artistJpaEntity) {
        this.id = id;
        this.videoUrl = videoUrl;
        this.deleted = deleted;
        this.artistJpaEntity = artistJpaEntity;
    }

    public void update(ArtistVideo artistVideo) {
        this.videoUrl = artistVideo.getVideoUrl();
    }

    public void delete() {
        this.deleted = true;
    }
}
