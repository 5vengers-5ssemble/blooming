package com.fivengers.blooming.form.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CalculateInfo {

    private String email;
    private String deposit;
    private String bankbookImg;

    public CalculateInfo(String email, String deposit, String bankbookImg) {
        this.email = email;
        this.deposit = deposit;
        this.bankbookImg = bankbookImg;
    }
}
