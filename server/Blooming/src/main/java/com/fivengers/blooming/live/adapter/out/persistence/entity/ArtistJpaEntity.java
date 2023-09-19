package com.fivengers.blooming.live.adapter.out.persistence.entity;

import com.fivengers.blooming.global.audit.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// TODO : merge 전 임시 클래스 --> 이후 삭제 예정

@Entity
@Table(name = "artist")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArtistJpaEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="artist_id")
    private Long id;

    @Column
    private String stageName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private MemberJpaEntity memberJpaEntity;

    public ArtistJpaEntity(Long id, String stageName, MemberJpaEntity memberJpaEntity) {
        this.id = id;
        this.stageName = stageName;
        this.memberJpaEntity = memberJpaEntity;
    }
}
