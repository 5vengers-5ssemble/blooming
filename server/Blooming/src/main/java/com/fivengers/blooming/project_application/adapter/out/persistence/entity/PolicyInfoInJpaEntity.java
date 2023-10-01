package com.fivengers.blooming.project_application.adapter.out.persistence.entity;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PolicyInfoInJpaEntity {

    private Boolean serviceAgreement;
    private Boolean refundAgreement;

    public PolicyInfoInJpaEntity(Boolean serviceAgreement, Boolean refundAgreement) {
        this.serviceAgreement = serviceAgreement;
        this.refundAgreement = refundAgreement;
    }
}
