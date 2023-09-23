package com.fivengers.blooming.form.adapter.out.persistence.mapper;


import com.fivengers.blooming.form.adapter.out.persistence.entity.CalculateInfoInForm;
import com.fivengers.blooming.form.domain.CalculateInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CalculateInfoMapper {

    public CalculateInfo toDomain(CalculateInfoInForm calculateInfo) {
        return new CalculateInfo(calculateInfo.getEmail(),
                calculateInfo.getDeposit(),
                calculateInfo.getBankbookImg());
    }

    public CalculateInfoInForm toInForm(CalculateInfo calculateInfo) {
        return new CalculateInfoInForm(calculateInfo.getEmail(),
                calculateInfo.getDeposit(),
                calculateInfo.getBankbookImg());
    }
}
