package com.fivengers.blooming.member.adapter.out.persistence.entity;

import com.fivengers.blooming.global.audit.BaseTime;
import com.fivengers.blooming.member.domain.MemberToken;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member_token")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTokenJpaEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_token_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String refreshToken;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberJpaEntity memberJpaEntity;

    @Builder
    public MemberTokenJpaEntity(Long id, String refreshToken, MemberJpaEntity memberJpaEntity) {
        this.id = id;
        this.refreshToken = refreshToken;
        this.memberJpaEntity = memberJpaEntity;
    }

    public void update(MemberToken memberToken) {
        this.refreshToken = memberToken.getRefreshToken();
    }
}
