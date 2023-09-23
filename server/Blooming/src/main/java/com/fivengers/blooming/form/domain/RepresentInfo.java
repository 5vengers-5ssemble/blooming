package com.fivengers.blooming.form.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RepresentInfo {

    private String representor;
    private CalculateInfo calculateInfo;

    public RepresentInfo(String representor, CalculateInfo calculateInfo) {
        this.representor = representor;
        this.calculateInfo = calculateInfo;
    }
}
