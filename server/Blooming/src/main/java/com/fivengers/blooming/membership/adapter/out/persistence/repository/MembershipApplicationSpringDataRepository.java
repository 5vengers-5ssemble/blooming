package com.fivengers.blooming.membership.adapter.out.persistence.repository;

import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipApplicationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipApplicationSpringDataRepository extends
        JpaRepository<MembershipApplicationJpaEntity, Long> {

}
