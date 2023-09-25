package com.fivengers.blooming.form.adapter.out.persistence.entity;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PolicyInfoInForm {

    private Boolean service;
    private Boolean refund;

    public PolicyInfoInForm(Boolean service, Boolean refund) {
        this.service = service;
        this.refund = refund;
    }
}
