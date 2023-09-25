package com.fivengers.blooming.form.adapter.out.persistence.mapper;


import com.fivengers.blooming.form.adapter.out.persistence.entity.PolicyInfoInForm;
import com.fivengers.blooming.form.domain.PolicyInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PolicyInfoMapper {

    public PolicyInfo toDomain(PolicyInfoInForm policyInfo) {

        return new PolicyInfo(policyInfo.getService(), policyInfo.getRefund());
    }

    public PolicyInfoInForm toInForm(PolicyInfo policyInfo) {

        return new PolicyInfoInForm(policyInfo.getService(), policyInfo.getRefund());
    }
}
