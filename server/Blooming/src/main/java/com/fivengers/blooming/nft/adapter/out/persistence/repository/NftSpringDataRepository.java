package com.fivengers.blooming.nft.adapter.out.persistence.repository;

import com.fivengers.blooming.nft.adapter.out.persistence.entity.NftJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftSpringDataRepository extends JpaRepository<NftJpaEntity, Long> {
}
