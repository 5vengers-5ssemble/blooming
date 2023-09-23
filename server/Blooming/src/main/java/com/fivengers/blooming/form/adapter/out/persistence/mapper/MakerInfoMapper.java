package com.fivengers.blooming.form.adapter.out.persistence.mapper;


import com.fivengers.blooming.form.adapter.out.persistence.entity.MakerInfoInForm;
import com.fivengers.blooming.form.domain.MakerInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MakerInfoMapper {

    public MakerInfo toDomain(MakerInfoInForm makerInfo) {

        return MakerInfo.builder()
                .makerNum(makerInfo.getMakerNum())
                .makerName(makerInfo.getMakerName())
                .makerAddFile(makerInfo.getMakerAddFile())
                .sealCertificate(makerInfo.getSealCertificate())
                .build();
    }

    public MakerInfoInForm toInForm(MakerInfo makerInfo) {

        return MakerInfoInForm.builder()
                .makerNum(makerInfo.getMakerNum())
                .makerName(makerInfo.getMakerName())
                .makerAddFile(makerInfo.getMakerAddFile())
                .sealCertificate(makerInfo.getSealCertificate())
                .build();
    }
}
