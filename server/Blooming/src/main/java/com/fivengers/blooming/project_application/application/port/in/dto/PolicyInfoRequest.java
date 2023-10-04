package com.fivengers.blooming.project_application.application.port.in.dto;

import com.fivengers.blooming.project_application.domain.PolicyInfo;
import jakarta.validation.constraints.NotNull;

public record PolicyInfoRequest(@NotNull Boolean service,
                                @NotNull Boolean refund) {

    public PolicyInfo toDomain() {
        return new PolicyInfo(service, refund);
    }
}
