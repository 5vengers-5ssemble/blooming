package com.fivengers.blooming.payment.domain;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.member.domain.Member;
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

    public boolean equals(Payment original, Payment target){
        if(!original.getMember().equals(target.getMember())) return false;
        if(!original.getArtist().equals(target.getArtist())) return false;
        if(!original.getOrderId().equals(target.getOrderId())) return false;
        if(!original.getProjectId().equals(target.getProjectId())) return false;
        if(!original.getProjectType().equals(target.getProjectType())) return false;
        if(!original.getAmount().equals(target.getAmount())) return false;
        return true;
    }
}
