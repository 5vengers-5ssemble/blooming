package com.fivengers.blooming.project_application.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MakerInfo {

    private String licenseNumber;
    private String companyName;
    private String licenseImage;
    private String sealCertificate;

    @Builder
    public MakerInfo(String licenseNumber, String companyName, String licenseImage,
            String sealCertificate) {
        this.licenseNumber = licenseNumber;
        this.companyName = companyName;
        this.licenseImage = licenseImage;
        this.sealCertificate = sealCertificate;
    }
}
