package com.fivengers.blooming.nft.port.out;

import com.fivengers.blooming.nft.domain.Nft;

public interface NftPort {

    Nft save(Nft nft);
}
