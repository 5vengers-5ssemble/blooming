package com.fivengers.blooming.payment.application.port.in.dto;

import com.fivengers.blooming.payment.domain.Payment;
import com.fivengers.blooming.project.domain.ProjectType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record TempPaymentCreateRequest(@NotNull Long projectId,
                                       @NotNull ProjectType projectType,
                                       @NotBlank String orderId,
                                       @Positive @NotNull Long amount
) {

    public Payment toDomain(Long memberId) {
        return Payment.builder()
                .orderId(orderId)
                .amount(amount)
                .projectId(projectId)
                .projectType(projectType)
                .memberId(memberId)
                .build();
    }
}
