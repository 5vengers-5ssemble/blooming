package com.fivengers.blooming.form.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectInfo {

    private String category;
    private MakerInfo makerInfo;
    private Long targetAmount;


    public ProjectInfo(String category, MakerInfo makerInfo, Long targetAmount) {
        this.category = category;
        this.makerInfo = makerInfo;
        this.targetAmount = targetAmount;
    }
}
