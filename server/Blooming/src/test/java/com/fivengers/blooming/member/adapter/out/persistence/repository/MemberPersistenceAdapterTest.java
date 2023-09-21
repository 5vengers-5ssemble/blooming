package com.fivengers.blooming.member.adapter.out.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.fivengers.blooming.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.entity.Oauth;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.Member;
import org.assertj.core.api.Assertions;
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
                .account("12345678")
                .deleted(false)
                .build();
        memberSpringDataRepository.save(member);
    }

    @Test
    @DisplayName("id로 멤버를 조회한다.")
    void findMemberById() {

        Member findMember = memberPersistenceAdapter.findById(member.getId());

        assertThat(findMember.getId()).isEqualTo(member.getId());
    }
}