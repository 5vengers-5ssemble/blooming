package com.fivengers.blooming.form.adapter.out.persistence.mapper;


import com.fivengers.blooming.form.adapter.out.persistence.entity.DefaultInfoInForm;
import com.fivengers.blooming.form.domain.DefaultInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultInfoMapper {

    public DefaultInfo toDomain(DefaultInfoInForm defaultInfo) {

        return DefaultInfo.builder()
                .image(defaultInfo.getImage())
                .startDate(defaultInfo.getStartDate())
                .endDate(defaultInfo.getEndDate())
                .title(defaultInfo.getTitle())
                .build();
    }

    public DefaultInfoInForm toInForm(DefaultInfo defaultInfo) {

        return DefaultInfoInForm.builder()
                .image(defaultInfo.getImage())
                .startDate(defaultInfo.getStartDate())
                .endDate(defaultInfo.getEndDate())
                .title(defaultInfo.getTitle())
                .build();
    }
}
