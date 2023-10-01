package com.fivengers.blooming.project_application.application.port.in.dto;

import com.fivengers.blooming.project_application.domain.MakerInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record MakerInfoRequest(@NotBlank @Size(min = 10, max = 10) String licenseNumber,
                               @NotBlank String companyName,
                               @NotBlank String licenseImage,
                               @NotBlank String sealCertificate) {

    public MakerInfo toDomain() {
        return MakerInfo.builder()
                .licenseNumber(licenseNumber)
                .companyName(companyName)
                .licenseImage(licenseImage)
                .sealCertificate(sealCertificate)
                .build();
    }
}
