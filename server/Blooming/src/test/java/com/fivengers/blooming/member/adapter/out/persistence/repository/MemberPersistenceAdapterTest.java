package com.fivengers.blooming.member.adapter.out.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.entity.Oauth;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberRole;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberPersistenceAdapterTest {

    @Autowired MemberSpringDataRepository memberSpringDataRepository;
    @Autowired MemberPersistenceAdapter memberPersistenceAdapter;

    MemberJpaEntity member;

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
    }

    @Test
    @DisplayName("id로 멤버를 조회한다.")
    void findMemberById() {

        Optional<Member> findMember = memberPersistenceAdapter.findById(member.getId());

        assertThat(findMember).isNotEmpty();
        assertThat(findMember.get().getId()).isEqualTo(member.getId());
    }
}