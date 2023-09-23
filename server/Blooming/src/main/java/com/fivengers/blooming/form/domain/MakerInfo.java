package com.fivengers.blooming.form.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MakerInfo {

    private String makerNum;
    private String makerName;
    private String makerAddFile;
    private String sealCertificate;

    @Builder
    public MakerInfo(String makerNum, String makerName, String makerAddFile,
            String sealCertificate) {
        this.makerNum = makerNum;
        this.makerName = makerName;
        this.makerAddFile = makerAddFile;
        this.sealCertificate = sealCertificate;
    }
}
