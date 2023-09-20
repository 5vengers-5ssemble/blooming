package com.fivengers.blooming.artist.adapter.out.persistence.entity;

import com.fivengers.blooming.global.audit.BaseTime;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "artist")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArtistJpaEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long id;

    @Column(nullable = false)
    private String stageName;

    @Column
    private String agency;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String profileImageUrl;

    @Column(nullable = false)
    private Boolean deleted;

    @OneToOne
    @JoinColumn(name = "member_id")
    private MemberJpaEntity memberJpaEntity;

    @Builder
    public ArtistJpaEntity(
            Long id,
            String stageName,
            String agency,
            String description,
            String profileImageUrl,
            Boolean deleted,
            MemberJpaEntity memberJpaEntity) {
        this.id = id;
        this.stageName = stageName;
        this.agency = agency;
        this.description = description;
        this.profileImageUrl = profileImageUrl;
        this.deleted = deleted;
        this.memberJpaEntity = memberJpaEntity;
    }

}
