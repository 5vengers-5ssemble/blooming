package com.fivengers.blooming.form.application.port.in.dto;

import com.fivengers.blooming.form.domain.MakerInfo;
import com.fivengers.blooming.form.domain.ProjectInfo;

public record ProjectInfoRequest(String category,
                                 MakerInfoRequest makerInfo,
                                 Long targetAmount) {

    public ProjectInfo toDomain() {
        return new ProjectInfo(category,
                makerInfo.toDomain(),
                targetAmount);
    }
}
