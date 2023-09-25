package com.fivengers.blooming.artistscrap.adapter.out.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.artist.adapter.out.persistence.repository.ArtistSpringDataRepository;
import com.fivengers.blooming.artistscrap.adapter.out.persistence.entity.ArtistScrapJpaEntity;
import com.fivengers.blooming.artistscrap.domain.ArtistScrap;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.entity.Oauth;
import com.fivengers.blooming.member.adapter.out.persistence.mapper.MemberMapper;
import com.fivengers.blooming.member.adapter.out.persistence.repository.MemberSpringDataRepository;
import com.fivengers.blooming.member.domain.AuthProvider;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
class ArtistScrapPersistenceAdapterTest {

    @Autowired
    MemberSpringDataRepository memberSpringDataRepository;
    @Autowired
    ArtistSpringDataRepository artistSpringDataRepository;
    @Autowired
    ArtistScrapSpringDataRepository artistScrapSpringDataRepository;
    @Autowired
    ArtistScrapPersistenceAdapter artistScrapPersistenceAdapter;
    @Autowired
    ArtistMapper artistMapper;
    @Autowired
    MemberMapper memberMapper;

    MemberJpaEntity member;
    ArtistJpaEntity artist;

    @BeforeEach
    void initObjects() {
        this.member = MemberJpaEntity.builder()
                .oauth(new Oauth(AuthProvider.KAKAO, "1234567"))
                .name("이지은")
                .nickname("아이유")
                .account("12345678")
                .deleted(false)
                .build();
        memberSpringDataRepository.save(this.member);
        this.artist = ArtistJpaEntity.builder()
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .profileImageUrl("https://image.com")
                .youtubeUrl("https://youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/ui")
                .snsUrl("https://instagram.com/ui")
                .memberJpaEntity(this.member)
                .deleted(false)
                .build();
        artistSpringDataRepository.save(this.artist);
    }

    @Test
    @DisplayName("스크랩을 저장한다.")
    void saveScrap() {
        ArtistScrap artistScrap = ArtistScrap.builder()
                .artist(artistMapper.toDomain(artist))
                .member(memberMapper.toDomain(member))
                .build();

        artistScrapPersistenceAdapter.saveScrap(artistScrap);

        Optional<ArtistScrapJpaEntity> findScrap = artistScrapSpringDataRepository.findByMemberJpaEntityIdAndArtistJpaEntityId(
                member.getId(),
                artist.getId());

        assertThat(findScrap).isNotEmpty();
    }

    @Test
    @DisplayName("스크랩을 중복 저장하면 예외가 발생한다.")
    void saveDuplicatedScrap() {
        ArtistScrap artistScrap = ArtistScrap.builder()
                .artist(artistMapper.toDomain(artist))
                .member(memberMapper.toDomain(member))
                .build();

        artistScrapSpringDataRepository.save(ArtistScrapJpaEntity.builder()
                .artistJpaEntity(artist)
                .memberJpaEntity(member)
                .build());

        Assertions.assertThatThrownBy(() -> artistScrapPersistenceAdapter.saveScrap(artistScrap))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("스크랩을 삭제한다.")
    void deleteScrap() {
        artistScrapSpringDataRepository.save(ArtistScrapJpaEntity.builder()
                .artistJpaEntity(artist)
                .memberJpaEntity(member)
                .build());

        artistScrapPersistenceAdapter.deleteScrap(member.getId(), artist.getId());

        Optional<ArtistScrapJpaEntity> findScrap = artistScrapSpringDataRepository
                .findByMemberJpaEntityIdAndArtistJpaEntityId(member.getId(), artist.getId());
        assertThat(findScrap).isEmpty();
    }
}