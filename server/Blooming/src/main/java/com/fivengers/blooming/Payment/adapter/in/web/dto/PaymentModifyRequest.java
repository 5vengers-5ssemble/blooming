package com.fivengers.blooming.payment.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;

public record PaymentModifyRequest(@NotNull String orderId) {

}
