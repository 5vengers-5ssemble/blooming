package com.fivengers.blooming.form.application.port.in.dto;

import com.fivengers.blooming.form.domain.CalculateInfo;

public record CalculateInfoRequest(String email,
                                   String deposit,
                                   String bankbookImage) {

    public CalculateInfo toDomain() {
        return new CalculateInfo(email, deposit, bankbookImage);
    }
}
