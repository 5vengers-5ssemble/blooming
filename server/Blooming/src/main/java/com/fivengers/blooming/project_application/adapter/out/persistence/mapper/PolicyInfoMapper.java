package com.fivengers.blooming.project_application.adapter.out.persistence.mapper;


import com.fivengers.blooming.project_application.adapter.out.persistence.entity.PolicyInfoInJpaEntity;
import com.fivengers.blooming.project_application.domain.PolicyInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PolicyInfoMapper {

    public PolicyInfo toDomain(PolicyInfoInJpaEntity policyInfo) {

        return new PolicyInfo(
                policyInfo.getServiceAgreement(),
                policyInfo.getRefundAgreement());
    }

    public PolicyInfoInJpaEntity toInJpaEntity(PolicyInfo policyInfo) {

        return new PolicyInfoInJpaEntity(
                policyInfo.getServiceAgreement(),
                policyInfo.getRefundAgreement());
    }
}
