package com.fivengers.blooming.form.adapter.out.persistence.mapper;


import com.fivengers.blooming.form.adapter.out.persistence.entity.RepresentInfoInForm;
import com.fivengers.blooming.form.domain.RepresentInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RepresentInfoMapper {

    private final CalculateInfoMapper calculateInfoMapper;

    public RepresentInfo toDomain(RepresentInfoInForm representInfo) {

        return new RepresentInfo(representInfo.getRepresentor(),
                calculateInfoMapper.toDomain(representInfo.getCalculateInfo()));
    }

    public RepresentInfoInForm toInForm(RepresentInfo representInfo) {

        return new RepresentInfoInForm(representInfo.getRepresentor(),
                calculateInfoMapper.toInForm(representInfo.getCalculateInfo()));
    }
}
