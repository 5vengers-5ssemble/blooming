package com.fivengers.blooming.membership.adapter.out.persistence.entity;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.global.audit.BaseTime;
import com.fivengers.blooming.membership.domain.MembershipApplication;
import com.fivengers.blooming.membership.domain.MembershipApplicationState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "membership_application")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MembershipApplicationJpaEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_application_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

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
    private Long salePrice;

    @Column(nullable = false)
    private String thumbnailUrl;

    @Column(nullable = false)
    private String baseUri;

    @Column(nullable = false)
    private String privateKey;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MembershipApplicationState applicationState;

    @Column(nullable = false)
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private ArtistJpaEntity artistJpaEntity;

    @Builder
    public MembershipApplicationJpaEntity(Long id,
                                          String title,
                                          String description,
                                          LocalDateTime seasonStart,
                                          LocalDateTime seasonEnd,
                                          LocalDateTime purchaseStart,
                                          LocalDateTime purchaseEnd,
                                          Integer saleCount,
                                          Long salePrice,
                                          String thumbnailUrl,
                                          String baseUri,
                                          String privateKey,
                                          MembershipApplicationState applicationState,
                                          Boolean deleted,
                                          ArtistJpaEntity artistJpaEntity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.seasonStart = seasonStart;
        this.seasonEnd = seasonEnd;
        this.purchaseStart = purchaseStart;
        this.purchaseEnd = purchaseEnd;
        this.saleCount = saleCount;
        this.salePrice = salePrice;
        this.thumbnailUrl = thumbnailUrl;
        this.baseUri = baseUri;
        this.privateKey = privateKey;
        this.applicationState = applicationState;
        this.deleted = deleted;
        this.artistJpaEntity = artistJpaEntity;
    }

    public void update(MembershipApplication membershipApplication) {
        this.title = membershipApplication.getTitle();
        this.description = membershipApplication.getDescription();
        this.seasonStart = membershipApplication.getSeasonStart();
        this.seasonEnd = membershipApplication.getSeasonEnd();
        this.purchaseStart = membershipApplication.getPurchaseStart();
        this.purchaseEnd = membershipApplication.getPurchaseEnd();
        this.thumbnailUrl = membershipApplication.getThumbnailUrl();
        this.applicationState = membershipApplication.getApplicationState();
    }
}
