package com.fivengers.blooming.fixture.nft.adapter.out.persistence;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.global.exception.nft.NftNotFoundException;
import com.fivengers.blooming.nft.domain.Nft;
import com.fivengers.blooming.nft.port.out.NftPort;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeNftPersistenceAdapter implements NftPort {

    private Map<Long, Nft> store = new HashMap<>();
    private Long autoIncrementId = 1L;

    @Override
    public Nft save(Nft nft) {
        if (isPersistenceObject(nft)) {
            store.put(nft.getId(), nft);
            return nft;
        }
        return persist(nft);
    }

    @Override
    public Optional<Nft> findById(Long nftId) {
        return store.values().stream()
                .filter(nft -> nft.getId().equals(nftId))
                .findFirst();
    }

    private static boolean isPersistenceObject(Nft nft) {
        return nft.getId() != null;
    }

    private Nft persist(Nft nft) {
        LocalDateTime now = LocalDateTime.now();
        Nft persistNft = Nft.builder()
                .id(autoIncrementId)
                .tokenId(nft.getTokenId())
                .contractAddress(nft.getContractAddress())
                .symbol(nft.getSymbol())
                .createdAt(now)
                .modifiedAt(now)
                .artist(nft.getArtist())
                .build();
        store.put(autoIncrementId, persistNft);
        autoIncrementId++;
        return persistNft;
    }
}
