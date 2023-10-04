package com.fivengers.blooming.nft.port.out;

import com.fivengers.blooming.nft.domain.Nft;
import java.util.Optional;

public interface NftPort {

    Nft save(Nft nft);
    Optional<Nft> findById(Long nftId);
}
