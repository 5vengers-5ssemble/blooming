package com.fivengers.blooming.fixture.member.adapter.out.persistence;

import com.fivengers.blooming.member.application.port.out.MemberPort;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.membership.domain.Membership;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeMemberPersistenceAdapter implements MemberPort {

    private Map<Long, Member> store = new HashMap<>();
    private Long autoIncrementId = 1L;

    public Member save(Member member) {
        if (isPersistenceObject(member)) {
            store.put(member.getId(), member);
            return member;
        }
        return persist(member);
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.ofNullable(store.get(memberId));
    }

    @Override
    public Optional<Member> findByOAuth2Account(String account) {
        return store.values().stream()
                .filter(member -> member.getOauthAccount().equals(account))
                .findFirst();
    }

    private static boolean isPersistenceObject(Member member) {
        return member.getId() != null;
    }

    private Member persist(Member member) {
        LocalDateTime now = LocalDateTime.now();
        Member persistMember = Member.builder()
                .id(autoIncrementId)
                .oauthProvider(member.getOauthProvider())
                .oauthAccount(member.getOauthAccount())
                .name(member.getName())
                .nickname(member.getNickname())
                .account(member.getAccount())
                .createdAt(now)
                .modifiedAt(now)
                .build();
        store.put(autoIncrementId, member);
        autoIncrementId++;
        return persistMember;
    }
}
