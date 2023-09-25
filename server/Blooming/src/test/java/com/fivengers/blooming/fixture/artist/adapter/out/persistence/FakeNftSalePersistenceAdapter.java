package com.fivengers.blooming.fixture.artist.adapter.out.persistence;

import com.fivengers.blooming.membership.application.port.out.NftSalePort;
import com.fivengers.blooming.membership.domain.NftSale;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class FakeNftSalePersistenceAdapter implements NftSalePort {

    private final Map<Long, NftSale> store = new HashMap<>();
    private Long autoIncrementId = 1L;

    public NftSale save(NftSale nftSale) {
        if (isPersistenceObject(nftSale)) {
            store.put(nftSale.getId(), nftSale);
            return nftSale;
        }
        return persist(nftSale);
    }

    private static boolean isPersistenceObject(NftSale nftSale) {
        return nftSale.getId() != null;
    }

    private NftSale persist(NftSale nftSale) {
        LocalDateTime now = LocalDateTime.now();
        NftSale persistedNftSale = NftSale.builder()
                .id(autoIncrementId)
                .totalNftCount(nftSale.getTotalNftCount())
                .soldNftCount(nftSale.getSoldNftCount())
                .totalNftAmount(nftSale.getTotalNftAmount())
                .soldNftAmount(nftSale.getSoldNftAmount())
                .createdAt(now)
                .modifiedAt(now)
                .build();
        store.put(autoIncrementId, persistedNftSale);
        autoIncrementId++;
        return persistedNftSale;
    }
}
