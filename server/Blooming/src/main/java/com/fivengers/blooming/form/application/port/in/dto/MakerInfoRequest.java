package com.fivengers.blooming.form.application.port.in.dto;

import com.fivengers.blooming.form.domain.MakerInfo;

public record MakerInfoRequest(String makerNum,
                               String makerName,
                               String makerAddFile,
                               String sealCertificate) {

    public MakerInfo toDomain() {
        return MakerInfo.builder()
                .makerNum(makerNum)
                .makerName(makerName)
                .makerAddFile(makerAddFile)
                .sealCertificate(sealCertificate)
                .build();
    }
}
