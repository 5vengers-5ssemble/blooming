package com.fivengers.blooming.form.adapter.out.persistence.entity;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CalculateInfoInForm {

    private String email;
    private String deposit;
    private String bankbookImg;

    public CalculateInfoInForm(String email, String deposit, String bankbookImg) {
        this.email = email;
        this.deposit = deposit;
        this.bankbookImg = bankbookImg;
    }
}
