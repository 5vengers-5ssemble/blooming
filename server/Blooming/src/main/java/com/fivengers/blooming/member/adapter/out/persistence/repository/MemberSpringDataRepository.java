package com.fivengers.blooming.member.adapter.out.persistence.repository;

import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSpringDataRepository extends JpaRepository<MemberJpaEntity, Long> {

}
