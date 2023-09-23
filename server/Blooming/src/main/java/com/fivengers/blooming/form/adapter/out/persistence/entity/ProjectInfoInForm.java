package com.fivengers.blooming.form.adapter.out.persistence.entity;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectInfoInForm {

    private String category;
    private MakerInfoInForm makerInfo;
    private Long targetAmount;


    public ProjectInfoInForm(String category, MakerInfoInForm makerInfo, Long targetAmount) {
        this.category = category;
        this.makerInfo = makerInfo;
        this.targetAmount = targetAmount;
    }
}
