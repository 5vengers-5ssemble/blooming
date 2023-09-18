package com.fivengers.blooming.Payment.domain;

import com.fivengers.blooming.Artist.domain.Artist;
import com.fivengers.blooming.Member.domain.Member;
import com.fivengers.blooming.global.audit.BaseTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Payment extends BaseTime {

    private Long id;
    private Member member;
    private Artist artist;
    private ProjectType projectType;
    private Long projectId;
    private String paymentKey;
    private String orderId;
    private Long amount;

    @Builder
    public Payment(Long id, Member member, Artist artist, ProjectType projectType, Long projectId,
            String paymentKey, String orderId, Long amount) {
        this.id = id;
        this.member = member;
        this.artist = artist;
        this.projectType = projectType;
        this.projectId = projectId;
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.amount = amount;
    }
}
