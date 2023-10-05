package com.fivengers.blooming.member.adapter.out.persistence.repository;

import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberTokenJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTokenSpringDataRepository extends JpaRepository<MemberTokenJpaEntity, Long> {

    Optional<MemberTokenJpaEntity> findByMemberJpaEntityId(Long memberJpaEntityId);
    Optional<MemberTokenJpaEntity> findByRefreshToken(String refreshToken);
}
