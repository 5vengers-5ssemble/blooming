package com.fivengers.blooming.form.adapter.out.persistence.entity;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoryInfoInForm {

    private String introduction;
    private String teaserVideoUrl;
    private MoreInfoInForm moreInfo;
    private Long budget;

    @Builder
    public StoryInfoInForm(String introduction, String teaserVideoUrl, MoreInfoInForm moreInfo, Long budget) {
        this.introduction = introduction;
        this.teaserVideoUrl = teaserVideoUrl;
        this.moreInfo = moreInfo;
        this.budget = budget;
    }
}
