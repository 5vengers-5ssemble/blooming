package com.fivengers.blooming.payment.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TempPaymentCreateRequest(long memberId,
                                       long artistId,
                                       String fundingType,
                                       long fundingId,
                                       String paymentKey,
                                       @NotNull String orderId,
                                       @NotBlank  Long amount) {

}
