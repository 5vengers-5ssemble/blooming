package com.fivengers.blooming.project_application.adapter.out.persistence.mapper;


import com.fivengers.blooming.project_application.adapter.out.persistence.entity.MakerInfoInJpaEntity;
import com.fivengers.blooming.project_application.domain.MakerInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MakerInfoMapper {

    public MakerInfo toDomain(MakerInfoInJpaEntity makerInfo) {

        return MakerInfo.builder()
                .licenseNumber(makerInfo.getLicenseNumber())
                .companyName(makerInfo.getCompanyName())
                .licenseImage(makerInfo.getLicenseImage())
                .sealCertificate(makerInfo.getSealCertificate())
                .build();
    }

    public MakerInfoInJpaEntity toInJpaEntity(MakerInfo makerInfo) {

        return MakerInfoInJpaEntity.builder()
                .licenseNumber(makerInfo.getLicenseNumber())
                .companyName(makerInfo.getCompanyName())
                .licenseImage(makerInfo.getLicenseImage())
                .sealCertificate(makerInfo.getSealCertificate())
                .build();
    }
}
