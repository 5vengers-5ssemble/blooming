package com.fivengers.blooming.artistscrap.adapter.out.persistence.entity;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "artist_scrap",
        uniqueConstraints = {
                @UniqueConstraint(name = "artistScrap",
                        columnNames = {"member_id", "artist_id"})})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArtistScrapJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_scrap_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberJpaEntity memberJpaEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = false)
    private ArtistJpaEntity artistJpaEntity;

    @Builder
    public ArtistScrapJpaEntity(Long id,
            MemberJpaEntity memberJpaEntity,
            ArtistJpaEntity artistJpaEntity) {
        this.id = id;
        this.memberJpaEntity = memberJpaEntity;
        this.artistJpaEntity = artistJpaEntity;
    }
}
