package com.fivengers.blooming.live.adapter.out.persistence.entity;

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
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "live")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LiveJpaEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "live_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private LocalDateTime endedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = false)
    private ArtistJpaEntity artistJpaEntity;

    @Builder
    public LiveJpaEntity(Long id, String title, LocalDateTime endedAt,
            ArtistJpaEntity artistJpaEntity) {
        this.id = id;
        this.title = title;
        this.endedAt = endedAt;
        this.artistJpaEntity = artistJpaEntity;
    }
}
