package com.fivengers.blooming.nft.adapter.out.persistence.repository;

import com.fivengers.blooming.nft.adapter.out.persistence.entity.NftOwnerInfoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftOwnerInfoSpringDataRepository extends JpaRepository<NftOwnerInfoJpaEntity, Long> {

}
