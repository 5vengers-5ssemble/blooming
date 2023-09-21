package com.fivengers.blooming.payment.application.port.in.dto;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.payment.domain.Payment;
import com.fivengers.blooming.payment.domain.ProjectType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

@Builder
public record TempPaymentCreateRequest(@NotNull ProjectType projectType,
                                       @NotNull Long projectId,
                                       @NotBlank String orderId,
                                       @PositiveOrZero @NotNull Long amount) {

    public Payment toDomain() {
        return Payment.builder()
                .orderId(orderId)
                .amount(amount)
                .projectId(projectId)
                .projectType(projectType)
                .build();
    }
}
