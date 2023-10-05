package com.fivengers.blooming.nft.adapter.out.persistence.entity;

import com.fivengers.blooming.global.audit.BaseTime;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "nft_owner_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NftOwnerInfoJpaEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nft_owner_info_id")
    private Long id;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean owned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberJpaEntity memberJpaEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nft_id", nullable = false, unique = true)
    private NftJpaEntity nftJpaEntity;

    @Builder
    public NftOwnerInfoJpaEntity(Long id, Boolean owned, MemberJpaEntity memberJpaEntity,
            NftJpaEntity nftJpaEntity) {
        this.id = id;
        this.owned = owned;
        this.memberJpaEntity = memberJpaEntity;
        this.nftJpaEntity = nftJpaEntity;
    }
}
