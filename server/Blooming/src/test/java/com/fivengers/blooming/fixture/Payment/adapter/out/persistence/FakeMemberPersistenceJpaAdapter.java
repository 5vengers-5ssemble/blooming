package com.fivengers.blooming.fixture.Payment.adapter.out.persistence;

import com.fivengers.blooming.member.domain.Member;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class FakeMemberPersistenceJpaAdapter {

    private final Map<Long, Member> store = new HashMap<>();
    private Long autoIncrementId = 1L;

    public Member save(Member member) {
        if (isPersistenceObject(member)) {
            store.put(member.getId(), member);
            return member;
        }
        return persist(member);
    }

    private boolean isPersistenceObject(Member member) {
        return member.getId() != null;
    }

    private Member persist(Member member) {
        LocalDateTime now = LocalDateTime.now();
        Member persistedMember = Member.builder()
                .id(autoIncrementId)
                .name(member.getName())
                .build();
        store.put(autoIncrementId, persistedMember);
        autoIncrementId++;
        return persistedMember;
    }
}
