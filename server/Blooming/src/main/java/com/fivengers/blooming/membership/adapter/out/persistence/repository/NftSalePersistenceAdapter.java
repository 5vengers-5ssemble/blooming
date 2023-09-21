package com.fivengers.blooming.membership.adapter.out.persistence.repository;

import com.fivengers.blooming.membership.application.port.out.NftSalePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NftSalePersistenceAdapter implements NftSalePort {

    private final NftSaleSpringDataRepository nftSaleSpringDataRepository;

}
