package com.fivengers.blooming.form.adapter.out.persistence.entity;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RepresentInfoInForm {

    private String representor;
    private CalculateInfoInForm calculateInfo;

    public RepresentInfoInForm(String representor, CalculateInfoInForm calculateInfo) {
        this.representor = representor;
        this.calculateInfo = calculateInfo;
    }
}
