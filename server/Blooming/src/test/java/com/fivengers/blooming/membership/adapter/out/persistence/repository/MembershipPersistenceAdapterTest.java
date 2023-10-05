package com.fivengers.blooming.membership.adapter.out.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.artist.adapter.out.persistence.repository.ArtistSpringDataRepository;
import com.fivengers.blooming.global.support.QuerydslRepositorySupport;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.entity.Oauth;
import com.fivengers.blooming.member.adapter.out.persistence.repository.MemberSpringDataRepository;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.MemberRole;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.MembershipJpaEntity;
import com.fivengers.blooming.membership.adapter.out.persistence.entity.NftSaleJpaEntity;
import com.fivengers.blooming.membership.domain.Membership;
import com.fivengers.blooming.nft.adapter.out.persistence.repository.NftSpringDataRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MembershipPersistenceAdapterTest {

    @Autowired MembershipSpringDataRepository membershipSpringDataRepository;
    @Autowired ArtistSpringDataRepository artistSpringDataRepository;
    @Autowired NftSpringDataRepository nftSpringDataRepository;
    @Autowired MemberSpringDataRepository memberSpringDataRepository;
    @Autowired MembershipPersistenceAdapter membershipPersistenceAdapter;
    MemberJpaEntity member;
    ArtistJpaEntity artist;

    @BeforeEach
    void initObjects() {
        member = MemberJpaEntity.builder()
                .oauth(new Oauth(AuthProvider.KAKAO, "1234567"))
                .name("이지은")
                .nickname("아이유")
                .deleted(false)
                .role(List.of(MemberRole.ROLE_USER))
                .build();
        memberSpringDataRepository.save(member);
        artist = ArtistJpaEntity.builder()
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .profileImageUrl("https://image.com")
                .youtubeUrl("https://youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/ui")
                .snsUrl("https://instagram.com/ui")
                .memberJpaEntity(member)
                .deleted(false)
                .build();
        artistSpringDataRepository.save(artist);
    }

    @Test
    @DisplayName("저장된 아티스트의 멤버십 중 가장 최근의 멤버십들만 가져온다.")
    void findLatestSeasonsMembership() {
        LocalDateTime now = LocalDateTime.now();
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
                .artistJpaEntity(artist)
                .nftSaleJpaEntity(NftSaleJpaEntity.builder()
                        .totalNftCount(1)
                        .soldNftCount(0)
                        .totalNftAmount(10000L)
                        .soldNftAmount(0L)
                        .deleted(false)
                        .build())
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
                .artistJpaEntity(artist)
                .nftSaleJpaEntity(NftSaleJpaEntity.builder()
                        .totalNftCount(1)
                        .soldNftCount(0)
                        .totalNftAmount(10000L)
                        .soldNftAmount(0L)
                        .deleted(false)
                        .build())
                .build();
        membershipSpringDataRepository.save(membership1);
        membershipSpringDataRepository.save(membership2);

        Page<Membership> memberships = membershipPersistenceAdapter.findLatestSeasons(PageRequest.of(0, 10,
                Sort.by("createdAt").descending()));
        assertThat(memberships).hasSize(1);
        assertThat(memberships.getContent().get(0).getId()).isEqualTo(membership2.getId());
    }

    @Test
    @DisplayName("저장된 아티스트의 멤버십 중 시즌이 진행중인 멤버십들만 가져온다.")
    void findByBetweenSeasonStartAndSeasonEnd() {
        LocalDateTime now = LocalDateTime.now();
        MembershipJpaEntity membership1 = MembershipJpaEntity.builder()
                .title("아이유 멤버십 시즌1")
                .description("아이유 멤버십1")
                .season(1)
                .seasonStart(now.minusHours(1L))
                .seasonEnd(now)
                .purchaseStart(now.minusMonths(1L))
                .purchaseEnd(now)
                .saleCount(0)
                .thumbnailUrl("https://image.com")
                .deleted(false)
                .artistJpaEntity(artist)
                .nftSaleJpaEntity(NftSaleJpaEntity.builder()
                        .totalNftCount(1)
                        .soldNftCount(0)
                        .totalNftAmount(10000L)
                        .soldNftAmount(0L)
                        .deleted(false)
                        .build())
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
                .artistJpaEntity(artist)
                .nftSaleJpaEntity(NftSaleJpaEntity.builder()
                        .totalNftCount(1)
                        .soldNftCount(0)
                        .totalNftAmount(10000L)
                        .soldNftAmount(0L)
                        .deleted(false)
                        .build())
                .build();
        membershipSpringDataRepository.save(membership1);
        membershipSpringDataRepository.save(membership2);

        Page<Membership> memberships = membershipPersistenceAdapter
                .findByBetweenSeasonStartAndSeasonEnd(PageRequest.of(0, 10,
                Sort.by("createdAt").descending()), LocalDateTime.now());
        assertThat(memberships).hasSize(1);
        assertThat(memberships.getContent().get(0).getId()).isEqualTo(membership2.getId());
    }
}