package com.fivengers.blooming.project_application.adapter.out.persistence.entity;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MakerInfoInJpaEntity {

    private String licenseNumber;
    private String companyName;
    private String licenseImage;
    private String sealCertificate;

    @Builder
    public MakerInfoInJpaEntity(String licenseNumber, String companyName, String licenseImage,
            String sealCertificate) {
        this.licenseNumber = licenseNumber;
        this.companyName = companyName;
        this.licenseImage = licenseImage;
        this.sealCertificate = sealCertificate;
    }
}
