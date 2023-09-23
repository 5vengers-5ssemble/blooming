package com.fivengers.blooming.form.adapter.out.persistence.entity;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MakerInfoInForm {

    private String makerNum;
    private String makerName;
    private String makerAddFile;
    private String sealCertificate;

    @Builder
    public MakerInfoInForm(String makerNum, String makerName, String makerAddFile,
            String sealCertificate) {
        this.makerNum = makerNum;
        this.makerName = makerName;
        this.makerAddFile = makerAddFile;
        this.sealCertificate = sealCertificate;
    }
}
