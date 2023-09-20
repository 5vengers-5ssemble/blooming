package com.fivengers.blooming.payment.adapter.in.web.dto;

import com.fivengers.blooming.payment.domain.Payment;
import com.fivengers.blooming.payment.domain.ProjectType;
import lombok.Builder;

@Builder
public record TempPaymentCreateResponse(Long projectId,
                                        ProjectType projectType,
                                        String orderId,
                                        Long amount) {

    public static TempPaymentCreateResponse from(Payment payment) {
        return TempPaymentCreateResponse.builder()
                .projectId(payment.getProjectId())
                .projectType(payment.getProjectType())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .build();
    }
}
