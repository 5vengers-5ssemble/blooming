package com.fivengers.blooming.Payment.adapter.in.web.dto;

import com.fivengers.blooming.Payment.domain.ProjectType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record TempPaymentCreateRequest(@NotNull Long memberId,
                                       @NotNull Long artistId,
                                       @NotEmpty ProjectType projectType,
                                       @NotNull Long projectId,
                                       @NotBlank String paymentKey,
                                       @NotBlank String orderId,
                                       @PositiveOrZero @NotNull Long amount) {

}
