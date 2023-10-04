package com.fivengers.blooming.artist.adapter.out.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import com.fivengers.blooming.artist.domain.ArtistApplication;
import com.fivengers.blooming.artist.domain.ArtistApplicationState;
import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.entity.Oauth;
import com.fivengers.blooming.member.adapter.out.persistence.mapper.MemberMapper;
import com.fivengers.blooming.member.adapter.out.persistence.repository.MemberSpringDataRepository;
import com.fivengers.blooming.member.domain.AuthProvider;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class ArtistApplicationAdapterTest {

    @Autowired
    MemberSpringDataRepository memberSpringDataRepository;
    @Autowired
    ArtistApplicationSpringDataRepository artistApplicationSpringDataRepository;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    ArtistApplicationAdapter artistApplicationAdapter;
    MemberJpaEntity member1;
    MemberJpaEntity member2;

    @BeforeEach
    void initObjects() {
        member1 = memberSpringDataRepository.save(MemberJpaEntity.builder()
                .oauth(new Oauth(AuthProvider.KAKAO, "1234567"))
                .name("이지은")
                .nickname("아이유")
                .account("12345678")
                .deleted(false)
                .build());
        member2 = memberSpringDataRepository.save(MemberJpaEntity.builder()
                .oauth(new Oauth(AuthProvider.KAKAO, "7654321"))
                .name("박효신")
                .nickname("박효신")
                .account("87654321")
                .deleted(false)
                .build());
    }

    @AfterEach
    void clearData() {
        artistApplicationSpringDataRepository.deleteAll();
    }

    @Test
    @DisplayName("아티스트 신청을 저장한다.")
    void saveArtistApplication() {
        LocalDateTime now = LocalDateTime.now();
        ArtistApplication artistApplication = ArtistApplication.builder()
                .stageName("아이유 (IU)")
                .description("아이유입니다.")
                .agency("EDAM 엔터테인먼트")
                .applicationState(ArtistApplicationState.APPLY)
                .profileImageUrl("https://image.com/iu")
                .youtubeUrl("https://www.youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/iu")
                .snsUrl("https://instagram.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .member(memberMapper.toDomain(member1))
                .build();

        ArtistApplication savedArtistApplication = artistApplicationAdapter.save(artistApplication);

        assertThat(savedArtistApplication.getStageName()).isEqualTo(
                artistApplication.getStageName());
    }

    @DisplayName("아티스트 신청을 상태별로 조회한다.")
    @ParameterizedTest(name = "{index}: state = {0}")
    @EnumSource(ArtistApplicationState.class)
    void findArtistApplicationByState(ArtistApplicationState state) {
        LocalDateTime now = LocalDateTime.now();
        artistApplicationAdapter.save(ArtistApplication.builder()
                .stageName("아이유 (IU)")
                .description("아이유입니다.")
                .agency("EDAM 엔터테인먼트")
                .applicationState(state)
                .profileImageUrl("https://image.com/iu")
                .youtubeUrl("https://www.youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/iu")
                .snsUrl("https://instagram.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .member(memberMapper.toDomain(member1))
                .build());
        artistApplicationAdapter.save(ArtistApplication.builder()
                .stageName("박효신")
                .description("박효신입니다.")
                .agency("허비그하로")
                .applicationState(state)
                .profileImageUrl("https://image.com/phs")
                .youtubeUrl("https://www.youtube.com/phs")
                .fanCafeUrl("https://cafe.daum.net/phs")
                .snsUrl("https://instagram.com/phs")
                .createdAt(now)
                .modifiedAt(now)
                .member(memberMapper.toDomain(member2))
                .build());

        Page<ArtistApplication> applications = artistApplicationAdapter.findByApplicationState(
                PageRequest.of(0, 10), state);

        assertThat(applications).hasSize(2);
        assertSoftly(as -> {
            as.assertThat(applications.getContent().get(0).getApplicationState()).isEqualTo(state);
            as.assertThat(applications.getContent().get(1).getApplicationState()).isEqualTo(state);
        });
    }

    @Test
    @DisplayName("상태가 null이면 전체 상태를 조회한다.")
    void findArtistApplications() {
        LocalDateTime now = LocalDateTime.now();
        artistApplicationAdapter.save(ArtistApplication.builder()
                .stageName("아이유 (IU)")
                .description("아이유입니다.")
                .agency("EDAM 엔터테인먼트")
                .applicationState(ArtistApplicationState.APPLY)
                .profileImageUrl("https://image.com/iu")
                .youtubeUrl("https://www.youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/iu")
                .snsUrl("https://instagram.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .member(memberMapper.toDomain(member1))
                .build());
        artistApplicationAdapter.save(ArtistApplication.builder()
                .stageName("박효신")
                .description("박효신입니다.")
                .agency("허비그하로")
                .applicationState(ArtistApplicationState.APPROVAL)
                .profileImageUrl("https://image.com/phs")
                .youtubeUrl("https://www.youtube.com/phs")
                .fanCafeUrl("https://cafe.daum.net/phs")
                .snsUrl("https://instagram.com/phs")
                .createdAt(now)
                .modifiedAt(now)
                .member(memberMapper.toDomain(member2))
                .build());

        Page<ArtistApplication> applications = artistApplicationAdapter.findByApplicationState(
                PageRequest.of(0, 10), null);

        assertThat(applications).hasSize(2);
        assertSoftly(as -> {
            as.assertThat(applications.getContent().get(0).getApplicationState())
                    .isEqualTo(ArtistApplicationState.APPLY);
            as.assertThat(applications.getContent().get(1).getApplicationState())
                    .isEqualTo(ArtistApplicationState.APPROVAL);
        });
    }

    @Test
    @DisplayName("아티스트 신청 ID로 아티스트를 조회한다.")
    void findArtistApplicationById() {
        LocalDateTime now = LocalDateTime.now();
        ArtistApplication application = artistApplicationAdapter.save(ArtistApplication.builder()
                .stageName("아이유 (IU)")
                .description("아이유입니다.")
                .agency("EDAM 엔터테인먼트")
                .applicationState(ArtistApplicationState.APPLY)
                .profileImageUrl("https://image.com/iu")
                .youtubeUrl("https://www.youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/iu")
                .snsUrl("https://instagram.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .member(memberMapper.toDomain(member1))
                .build());

        Optional<ArtistApplication> findApplication =
                artistApplicationAdapter.findById(application.getId());

        assertThat(findApplication).isNotEmpty();
        assertThat(findApplication.get().getId()).isEqualTo(application.getId());
    }

    @Test
    @DisplayName("아티스트 신청 정보를 업데이트한다.")
    void updateArtistApplication() {
        LocalDateTime now = LocalDateTime.now();
        ArtistApplication application = artistApplicationAdapter.save(ArtistApplication.builder()
                .stageName("아이유 (IU)")
                .description("아이유입니다.")
                .agency("EDAM 엔터테인먼트")
                .applicationState(ArtistApplicationState.APPLY)
                .profileImageUrl("https://image.com/iu")
                .youtubeUrl("https://www.youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/iu")
                .snsUrl("https://instagram.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .member(memberMapper.toDomain(member1))
                .build());

        ArtistApplication willUpdate = ArtistApplication.builder()
                .id(application.getId())
                .stageName("아이유 (IU)")
                .description("아이유입니다.")
                .agency("EDAM 엔터테인먼트")
                .applicationState(ArtistApplicationState.APPROVAL)
                .profileImageUrl("https://image.com/iu")
                .youtubeUrl("https://www.youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/iu")
                .snsUrl("https://instagram.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .member(memberMapper.toDomain(member1))
                .build();
        ArtistApplication updatedApplication = artistApplicationAdapter.update(willUpdate);

        assertThat(updatedApplication.getId()).isEqualTo(application.getId());
        assertThat(updatedApplication.getApplicationState())
                .isEqualTo(willUpdate.getApplicationState());
    }

}