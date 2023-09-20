package com.fivengers.blooming.membership.adapter.out.persistence.repository;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.artist.adapter.out.persistence.repository.ArtistSpringDataRepository;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.NftSaleJpaEntity;
import com.fivengers.blooming.membership.domain.Membership;
import com.fivengers.blooming.nft.adapter.out.persistence.entity.NftJpaEntity;
import com.fivengers.blooming.nft.adapter.out.persistence.repository.NftSpringDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MembershipPersistenceAdapterTest {

    @Autowired MembershipSpringDataRepository membershipSpringDataRepository;
    @Autowired ArtistSpringDataRepository artistSpringDataRepository;
    @Autowired NftSpringDataRepository nftSpringDataRepository;
    @Autowired MembershipPersistenceAdapter membershipPersistenceAdapter;
    ArtistJpaEntity artist;
    NftJpaEntity nft1;
    NftJpaEntity nft2;

    @BeforeEach
    void initObjects() {
        artist = ArtistJpaEntity.builder()
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .deleted(false)
                .build();
        artistSpringDataRepository.save(artist);
        nft1 = NftJpaEntity.builder()
                .tokenId("abcdefghijklmnopqrstuvwxyz1234567890")
                .contractAddress("1234567890abcdefghijklmnopqrstuvwxyz")
                .symbol("IU")
                .deleted(false)
                .artist(artist)
                .build();
        nft2 = NftJpaEntity.builder()
                .tokenId("aaa1234567890")
                .contractAddress("1234567890aaa")
                .symbol("IU")
                .deleted(false)
                .artist(artist)
                .build();
        nftSpringDataRepository.save(nft1);
        nftSpringDataRepository.save(nft2);
    }

    @Test
    @DisplayName("저장된 아티스트의 멤버십 중 가장 최근의 멤버십들만 가져온다.")
    void findLatestSeasonsMembership() {
        LocalDateTime now = LocalDateTime.now();
        NftSaleJpaEntity nftSale = NftSaleJpaEntity.builder()
                .totalNftCount(1)
                .soldNftCount(0)
                .totalNftAmount(10000L)
                .soldNftAmount(0L)
                .deleted(false)
                .build();
        MembershipJpaEntity membership1 = MembershipJpaEntity.builder()
                .title("아이유 멤버십 시즌1")
                .description("아이유 멤버십1")
                .season(1)
                .seasonStart(now)
                .seasonEnd(now.plusMonths(1L))
                .purchaseStart(now)
                .purchaseEnd(now.plusMonths(1L))
                .saleCount(0)
                .thumbnailUrl("https://image.com")
                .deleted(false)
                .nftJpaEntity(nft1)
                .artistJpaEntity(artist)
                .nftSaleJpaEntity(nftSale)
                .build();
        MembershipJpaEntity membership2 = MembershipJpaEntity.builder()
                .title("아이유 멤버십 시즌2")
                .description("아이유 멤버십2")
                .season(2)
                .seasonStart(now)
                .seasonEnd(now.plusMonths(1L))
                .purchaseStart(now)
                .purchaseEnd(now.plusMonths(1L))
                .saleCount(0)
                .thumbnailUrl("https://image.com")
                .deleted(false)
                .nftJpaEntity(nft1)
                .artistJpaEntity(artist)
                .nftSaleJpaEntity(nftSale)
                .build();
        membershipSpringDataRepository.save(membership1);
        membershipSpringDataRepository.save(membership2);

        Page<Membership> memberships = membershipPersistenceAdapter.findLatestSeasons(PageRequest.of(0, 10));
        assertThat(memberships).hasSize(1);
        assertThat(memberships.getContent().get(0).getId()).isEqualTo(membership2.getId());
    }
}