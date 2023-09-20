package com.fivengers.blooming.payment.adapter.in.web.dto;

import com.fivengers.blooming.payment.domain.Payment;
import com.fivengers.blooming.payment.domain.ProjectType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public record PaymentModifyResponse(Long memberId,
                                    Long artistId,
                                    Long projectId,
                                    String paymentKey,
                                    ProjectType projectType,
                                    String orderId,
                                    Long amount) {

    public static PaymentModifyResponse from(Payment payment) {
        return PaymentModifyResponse.builder()
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
