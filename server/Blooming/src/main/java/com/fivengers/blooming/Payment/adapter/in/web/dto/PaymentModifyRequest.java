package com.fivengers.blooming.Payment.adapter.in.web.dto;

import com.fivengers.blooming.Artist.domain.Artist;
import com.fivengers.blooming.Member.domain.Member;
import com.fivengers.blooming.Payment.domain.Payment;
import com.fivengers.blooming.Payment.domain.ProjectType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record PaymentModifyRequest (@NotNull Long memberId,
                                    @NotNull Long artistId,
                                    @NotNull ProjectType projectType,
                                    @NotNull String paymentKey,
                                    @NotNull Long projectId,
                                    @NotBlank String orderId,
                                    @PositiveOrZero @NotNull Long amount){
    public Payment toDomain(Member member, Artist artist) {
        return Payment.builder()
                .orderId(orderId)
                .amount(amount)
                .projectId(projectId)
                .projectType(projectType)
                .member(member)
                .artist(artist)
                .build();
    }

}
