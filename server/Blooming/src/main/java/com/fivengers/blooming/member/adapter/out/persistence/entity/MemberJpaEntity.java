package com.fivengers.blooming.member.adapter.out.persistence.entity;

import com.fivengers.blooming.global.audit.BaseTime;
import com.fivengers.blooming.member.domain.MemberRole;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberJpaEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Embedded
    private Oauth oauth;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String account;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<MemberRole> role = new ArrayList<>(List.of(MemberRole.ROLE_USER));

    @Column(nullable = false)
    private Boolean deleted;

    public MemberJpaEntity(Long id,
                           Oauth oauth,
                           String name,
                           String nickname,
                           String account,
                           Boolean deleted) {
        this.id = id;
        this.oauth = oauth;
        this.name = name;
        this.nickname = nickname;
        this.account = account;
        this.deleted = deleted;
    }
}
