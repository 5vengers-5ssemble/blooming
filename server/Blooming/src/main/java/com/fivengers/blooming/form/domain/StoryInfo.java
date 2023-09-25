package com.fivengers.blooming.form.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoryInfo {

    private String introduction;
    private String teaserVideoUrl;
    private MoreInfo moreInfo;
    private Long budget;

    @Builder
    public StoryInfo(String introduction, String teaserVideoUrl, MoreInfo moreInfo, Long budget) {
        this.introduction = introduction;
        this.teaserVideoUrl = teaserVideoUrl;
        this.moreInfo = moreInfo;
        this.budget = budget;
    }
}
