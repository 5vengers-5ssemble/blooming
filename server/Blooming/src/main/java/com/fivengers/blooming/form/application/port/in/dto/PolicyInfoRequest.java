package com.fivengers.blooming.form.application.port.in.dto;

import com.fivengers.blooming.form.domain.PolicyInfo;

public record PolicyInfoRequest(Boolean service,
                                Boolean refund) {

    public PolicyInfo toDomain() {
        return new PolicyInfo(service, refund);
    }
}
