package com.fivengers.blooming.Payment.adapter.in.web.dto;

import com.fivengers.blooming.Payment.domain.Payment;
import com.fivengers.blooming.Payment.domain.ProjectType;
import lombok.Builder;

@Builder
public record TempPaymentCreateResponse(Long memberId,
                                        Long artistId,
                                        Long projectId,
                                        ProjectType projectType,
                                        String paymentKey,
                                        String orderId,
                                        Long amount) {

    public static TempPaymentCreateResponse from(Payment payment) {
        return TempPaymentCreateResponse.builder()
                .memberId(payment.getMember().getId())
                .artistId(payment.getArtist().getId())
                .projectId(payment.getProjectId())
                .projectType(payment.getProjectType())
                .paymentKey(payment.getPaymentKey())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .build();
    }
}
