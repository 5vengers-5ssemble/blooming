package com.fivengers.blooming.payment.application.port.in.dto;

import com.fivengers.blooming.payment.domain.Payment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record PaymentCompareToTempRequest(@NotBlank String orderId,
                                          @PositiveOrZero @NotNull Long amount){
    public Payment toDomain() {
        return Payment.builder()
                .orderId(orderId)
                .amount(amount)
                .build();
    }

}
