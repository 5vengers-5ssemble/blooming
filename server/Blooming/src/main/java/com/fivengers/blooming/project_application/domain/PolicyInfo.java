package com.fivengers.blooming.project_application.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PolicyInfo {

    private Boolean serviceAgreement;
    private Boolean refundAgreement;

    public PolicyInfo(Boolean serviceAgreement, Boolean refundAgreement) {
        this.serviceAgreement = serviceAgreement;
        this.refundAgreement = refundAgreement;
    }
}
