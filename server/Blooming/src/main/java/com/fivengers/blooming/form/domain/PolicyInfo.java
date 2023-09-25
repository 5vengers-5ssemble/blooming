package com.fivengers.blooming.form.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PolicyInfo {

    private Boolean service;
    private Boolean refund;

    public PolicyInfo(Boolean service, Boolean refund) {
        this.service = service;
        this.refund = refund;
    }
}
