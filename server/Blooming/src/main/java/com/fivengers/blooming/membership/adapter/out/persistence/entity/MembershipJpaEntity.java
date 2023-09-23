package com.fivengers.blooming.membership.adapter.out.persistence.entity;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.global.audit.BaseTime;
import com.fivengers.blooming.nft.adapter.out.persistence.entity.NftJpaEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "membership")
@Getter
@Where(clause = "deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MembershipJpaEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer season;

    @Column(nullable = false)
    private LocalDateTime seasonStart;

    @Column(nullable = false)
    private LocalDateTime seasonEnd;

    @Column(nullable = false)
    private LocalDateTime purchaseStart;

    @Column(nullable = false)
    private LocalDateTime purchaseEnd;

    @Column(nullable = false)
    private Integer saleCount;

    @Column(nullable = false)
    private String thumbnailUrl;

    @Column(nullable = false)
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private ArtistJpaEntity artistJpaEntity;

    @OneToOne(mappedBy = "membershipJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private NftSaleJpaEntity nftSaleJpaEntity;

    @Builder
    public MembershipJpaEntity(Long id,
                               String title,
                               String description,
                               Integer season,
                               LocalDateTime seasonStart,
                               LocalDateTime seasonEnd,
                               LocalDateTime purchaseStart,
                               LocalDateTime purchaseEnd,
                               Integer saleCount,
                               String thumbnailUrl,
                               Boolean deleted,
                               ArtistJpaEntity artistJpaEntity,
                               NftSaleJpaEntity nftSaleJpaEntity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.season = season;
        this.seasonStart = seasonStart;
        this.seasonEnd = seasonEnd;
        this.purchaseStart = purchaseStart;
        this.purchaseEnd = purchaseEnd;
        this.saleCount = saleCount;
        this.thumbnailUrl = thumbnailUrl;
        this.deleted = deleted;
        this.artistJpaEntity = artistJpaEntity;
        setNftSaleJpaEntity(nftSaleJpaEntity);
    }

    private void setNftSaleJpaEntity(NftSaleJpaEntity nftSaleJpaEntity) {
        this.nftSaleJpaEntity = nftSaleJpaEntity;
        nftSaleJpaEntity.setMembershipJpaEntity(this);
    }
}
