package com.fivengers.blooming.live.adapter.out.persistence.entity;

import com.fivengers.blooming.global.audit.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// TODO : merge 전 임시 클래스 --> 이후 삭제 예정

@Entity
@Table(name="member")
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class MemberJpaEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column
    private String profileImageUrl;

    public MemberJpaEntity(Long id, String profileImageUrl) {
        this.id = id;
        this.profileImageUrl = profileImageUrl;
    }
}
