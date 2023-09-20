package com.fivengers.blooming.membership.adapter.out.persistence.repository;

import com.fivengers.blooming.membership.adapter.out.persistence.entity.NftSaleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftSaleSpringDataRepository extends JpaRepository<NftSaleJpaEntity, Long> {

}
