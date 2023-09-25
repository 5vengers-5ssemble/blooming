package com.fivengers.blooming.form.application.port.in.dto;

import com.fivengers.blooming.form.domain.RepresentInfo;

public record RepresentInfoRequest(String representor,
                                   CalculateInfoRequest calculateInfo) {

    public RepresentInfo toDomain() {
        return new RepresentInfo(representor, calculateInfo.toDomain());
    }
}
