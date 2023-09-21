package com.fivengers.blooming.payment.application.port.in.dto;

import jakarta.validation.constraints.NotNull;

public record PaymentModifyRequest(@NotNull String orderId,
                                   @NotNull String paymentKey) {

}
