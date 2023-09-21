package com.fivengers.blooming.nft.adapter.out.persistence.entity;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.global.audit.BaseTime;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipJpaEntity;
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
@Table(name = "nft")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftJpaEntity extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nft_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String tokenId;

    @Column(nullable = false)
    private String contractAddress;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_id")
    private MembershipJpaEntity membership;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private ArtistJpaEntity artist;

    @Builder
    public NftJpaEntity(
            Long id,
            String tokenId,
            String contractAddress,
            String symbol,
            Boolean deleted,
            MembershipJpaEntity membership,
            ArtistJpaEntity artist) {
        this.id = id;
        this.tokenId = tokenId;
        this.contractAddress = contractAddress;
        this.symbol = symbol;
        this.deleted = deleted;
        this.membership = membership;
        this.artist = artist;
    }
}
