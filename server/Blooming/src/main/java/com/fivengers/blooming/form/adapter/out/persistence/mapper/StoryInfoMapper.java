package com.fivengers.blooming.form.adapter.out.persistence.mapper;


import com.fivengers.blooming.form.adapter.out.persistence.entity.StoryInfoInForm;
import com.fivengers.blooming.form.domain.StoryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoryInfoMapper {

    private final MoreInfoMapper moreInfoMapper;

    public StoryInfo toDomain(StoryInfoInForm storyInfo) {

        return StoryInfo.builder()
                .introduction(storyInfo.getIntroduction())
                .teaserVideoUrl(storyInfo.getTeaserVideoUrl())
                .moreInfo(moreInfoMapper.toDomain(storyInfo.getMoreInfo()))
                .budget(storyInfo.getBudget())
                .build();
    }

    public StoryInfoInForm toInForm(StoryInfo storyInfo) {

        return StoryInfoInForm.builder()
                .introduction(storyInfo.getIntroduction())
                .teaserVideoUrl(storyInfo.getTeaserVideoUrl())
                .moreInfo(moreInfoMapper.toInForm(storyInfo.getMoreInfo()))
                .budget(storyInfo.getBudget())
                .build();
    }
}
