package com.fivengers.blooming.Payment.adapter.in.web.dto;

import com.fivengers.blooming.Payment.domain.ProjectType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record TempPaymentCreateResponse (Long memberId,
                                         Long artistId,
                                         Long projectId,
                                         ProjectType projectType,
                                         String paymentKey,
                                         String orderId,
                                         Long amount) {

}
