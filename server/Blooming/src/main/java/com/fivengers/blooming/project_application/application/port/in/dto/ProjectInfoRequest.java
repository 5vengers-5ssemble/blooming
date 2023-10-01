package com.fivengers.blooming.project_application.application.port.in.dto;

import com.fivengers.blooming.payment.domain.ProjectType;
import com.fivengers.blooming.project_application.domain.ProjectInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record ProjectInfoRequest(@NotBlank String category,
                                 @NotNull @Valid MakerInfoRequest makerInfo,
                                 @NotNull
                                 @Min(500_000) @Max(100_000_000)
                                 Long targetAmount) {

    public ProjectInfo toDomain() {
        return new ProjectInfo(
                ProjectType.from(category),
                makerInfo.toDomain(),
                targetAmount);
    }
}
