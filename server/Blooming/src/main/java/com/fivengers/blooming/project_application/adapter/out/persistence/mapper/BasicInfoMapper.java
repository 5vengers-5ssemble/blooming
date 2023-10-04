package com.fivengers.blooming.project_application.adapter.out.persistence.mapper;


import com.fivengers.blooming.project_application.adapter.out.persistence.entity.BasicInfoInJpaEntity;
import com.fivengers.blooming.project_application.domain.BasicInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BasicInfoMapper {

    public BasicInfo toDomain(BasicInfoInJpaEntity basicInfo) {

        return BasicInfo.builder()
                .title(basicInfo.getTitle())
                .thumbnail(basicInfo.getThumbnail())
                .startDate(basicInfo.getStartDate())
                .endDate(basicInfo.getEndDate())
                .build();
    }

    public BasicInfoInJpaEntity toInJpaEntity(BasicInfo basicInfo) {

        return BasicInfoInJpaEntity.builder()
                .title(basicInfo.getTitle())
                .thumbnail(basicInfo.getThumbnail())
                .startDate(basicInfo.getStartDate())
                .endDate(basicInfo.getEndDate())
                .build();
    }
}
