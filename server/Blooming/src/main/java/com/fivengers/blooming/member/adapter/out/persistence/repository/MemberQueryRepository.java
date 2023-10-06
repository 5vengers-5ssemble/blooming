package com.fivengers.blooming.member.adapter.out.persistence.repository;

import static com.fivengers.blooming.member.adapter.out.persistence.entity.QMemberJpaEntity.memberJpaEntity;

import com.fivengers.blooming.global.support.QuerydslRepositorySupport;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemberQueryRepository extends QuerydslRepositorySupport {

    public MemberQueryRepository() {
        super(MemberJpaEntity.class);
    }

    public Optional<MemberJpaEntity> findById(Long memberId) {
        return Optional.ofNullable(selectFrom(memberJpaEntity)
                .leftJoin(memberJpaEntity.role).fetchJoin()
                .where(memberJpaEntity.id.eq(memberId))
                .fetchOne());
    }

    public Optional<MemberJpaEntity> findByOAuth2Account(String account) {
        return Optional.ofNullable(selectFrom(memberJpaEntity)
                .leftJoin(memberJpaEntity.role).fetchJoin()
                .where(memberJpaEntity.oauth.oauthAccount.eq(account))
                .fetchOne());
    }
}
