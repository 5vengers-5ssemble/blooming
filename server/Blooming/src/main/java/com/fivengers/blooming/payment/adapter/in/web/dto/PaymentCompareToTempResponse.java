package com.fivengers.blooming.payment.adapter.in.web.dto;

import lombok.Builder;

@Builder
public record PaymentCompareToTempResponse(Boolean sameAsTemp) {

    public static PaymentCompareToTempResponse from(Boolean sameAsTemp) {
        return PaymentCompareToTempResponse.builder()
                .sameAsTemp(sameAsTemp)
                .build();
    }

}
