package com.fivengers.blooming.project_application.adapter.out.persistence.mapper;


import com.fivengers.blooming.project_application.adapter.out.persistence.entity.StoryInfoInJpaEntity;
import com.fivengers.blooming.project_application.domain.StoryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoryInfoMapper {

    public StoryInfo toDomain(StoryInfoInJpaEntity storyInfo) {

        return StoryInfo.builder()
                .introduction(storyInfo.getIntroduction())
                .description(storyInfo.getDescription())
                .listImage(storyInfo.getListImage())
                .compositionImage(storyInfo.getCompositionImage())
                .teaserVideoUrl(storyInfo.getTeaserVideoUrl())
                .budget(storyInfo.getBudget())
                .build();
    }

    public StoryInfoInJpaEntity toInJpaEntity(StoryInfo storyInfo) {

        return StoryInfoInJpaEntity.builder()
                .introduction(storyInfo.getIntroduction())
                .description(storyInfo.getDescription())
                .listImage(storyInfo.getListImage())
                .compositionImage(storyInfo.getCompositionImage())
                .teaserVideoUrl(storyInfo.getTeaserVideoUrl())
                .budget(storyInfo.getBudget())
                .build();
    }
}
