package com.fivengers.blooming.payment.adapter.in.web.dto;

import com.fivengers.blooming.payment.domain.Payment;
import com.fivengers.blooming.payment.domain.ProjectType;
import lombok.Builder;
import lombok.Getter;

@Builder
public record PaymentCompareToTempResponse(Boolean sameAsTemp) {

    public static PaymentCompareToTempResponse from(Boolean sameAsTemp) {
        return PaymentCompareToTempResponse.builder()
                .sameAsTemp(sameAsTemp)
                .build();
    }

}
