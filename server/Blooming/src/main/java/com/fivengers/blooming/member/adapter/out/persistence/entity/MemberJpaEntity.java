package com.fivengers.blooming.member.adapter.out.persistence.entity;

import com.fivengers.blooming.global.audit.BaseTime;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberRole;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "member")
@Getter
@Where(clause = "deleted = false")
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

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<MemberRole> role;

    @Column(nullable = false)
    private Boolean deleted;

    @Builder
    public MemberJpaEntity(Long id,
                           Oauth oauth,
                           String name,
                           String nickname,
                           Boolean deleted,
                           List<MemberRole> role) {
        this.id = id;
        this.oauth = oauth;
        this.name = name;
        this.nickname = nickname;
        this.deleted = deleted;
        this.role = role;
    }

    public void update(Member member) {
        this.nickname = member.getNickname();
        this.role = new ArrayList<>(member.getRole());
    }
}
