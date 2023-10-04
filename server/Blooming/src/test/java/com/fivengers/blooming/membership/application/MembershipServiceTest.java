package com.fivengers.blooming.membership.application;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.fixture.artist.adapter.out.persistence.FakeArtistPersistenceAdapter;
import com.fivengers.blooming.fixture.membership.adapter.out.persistence.FakeMembershipPersistenceAdapter;
import com.fivengers.blooming.fixture.nft.adapter.out.persistence.FakeNftPersistenceAdapter;
import com.fivengers.blooming.membership.domain.Membership;
import com.fivengers.blooming.membership.domain.NftSale;
import com.fivengers.blooming.nft.domain.Nft;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class MembershipServiceTest {

    FakeMembershipPersistenceAdapter membershipPersistenceAdapter;
    FakeArtistPersistenceAdapter artistPersistenceAdapter;
    MembershipService membershipService;
    Artist artist;

    @BeforeEach
    void initObjects() {
        this.membershipPersistenceAdapter = new FakeMembershipPersistenceAdapter();
        membershipService = new MembershipService(this.membershipPersistenceAdapter,
                                                  this.artistPersistenceAdapter);
        LocalDateTime now = LocalDateTime.now();
        this.artist = Artist.builder()
                .id(1L)
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .createdAt(now)
                .modifiedAt(now)
                .build();
    }

    @Test
    @DisplayName("각 아티스트별 가장 최근 시즌 멤버십을 검색한다.")
    void searchMembershipsByLatestSeason() {
        LocalDateTime now = LocalDateTime.now();
        NftSale nftSale = NftSale.builder()
                .id(1L)
                .totalNftCount(1)
                .soldNftCount(0)
                .totalNftAmount(10000L)
                .soldNftAmount(0L)
                .createdAt(now)
                .modifiedAt(now)
                .build();
        Membership membership1 = Membership.builder()
                .id(null)
                .title("아이유 (IU) 첫 번째 멤버십")
                .description("아이유 첫 번째 멤버십입니다.")
                .season(1)
                .seasonStart(now)
                .seasonEnd(now.plusYears(1))
                .purchaseStart(now)
                .purchaseEnd(now.plusMonths(1))
                .thumbnailUrl("https://image.com")
                .createdAt(now)
                .modifiedAt(now)
                .artist(artist)
                .nftSale(nftSale)
                .build();
        Membership membership2 = Membership.builder()
                .id(null)
                .title("아이유 (IU) 첫 번째 멤버십")
                .description("아이유 첫 번째 멤버십입니다.")
                .season(2)
                .seasonStart(now)
                .seasonEnd(now.plusYears(1))
                .purchaseStart(now)
                .purchaseEnd(now.plusMonths(1))
                .thumbnailUrl("https://image.com")
                .createdAt(now)
                .modifiedAt(now)
                .artist(artist)
                .nftSale(nftSale)
                .build();
        membershipPersistenceAdapter.save(membership1);
        membershipPersistenceAdapter.save(membership2);

        Page<Membership> memberships = membershipService.searchLatestSeasons(PageRequest.of(0, 10));

        assertThat(memberships.getContent()).hasSize(1);
        assertThat(memberships.getContent().get(0).getSeason()).isEqualTo(2);
    }
}