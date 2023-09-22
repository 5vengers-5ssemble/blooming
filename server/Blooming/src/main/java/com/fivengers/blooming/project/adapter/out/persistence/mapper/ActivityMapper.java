package com.fivengers.blooming.project.adapter.out.persistence.mapper;


import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.project.adapter.out.persistence.entity.ActivityJpaEntity;
import com.fivengers.blooming.project.domain.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActivityMapper {

    private final ArtistMapper artistMapper;

    public Activity toDomain(ActivityJpaEntity activityJpaEntity) {
        return Activity.builder()
                .id(activityJpaEntity.getId())
                .name(activityJpaEntity.getName())
                .fundingAmount(activityJpaEntity.getFundingAmount())
                .targetAmount(activityJpaEntity.getTargetAmount())
                .startedAt(activityJpaEntity.getStartedAt())
                .endedAt(activityJpaEntity.getEndedAt())
                .introduction(activityJpaEntity.getIntroduction())
                .description(activityJpaEntity.getDescription())
                .teaserVideoUrl(activityJpaEntity.getTeaserVideoUrl())
                .revenuePercent(activityJpaEntity.getRevenuePercent())
                .profileImg(activityJpaEntity.getProfileImg())
                .createdAt(activityJpaEntity.getCreatedAt())
                .modifiedAt(activityJpaEntity.getModifiedAt())
                .artist(artistMapper.toDomain(activityJpaEntity.getArtist()))
                .albumImgUrl(activityJpaEntity.getAlbumImgUrl())
                .tracklistImgUrl(activityJpaEntity.getTracklistImgUrl())
                .compositionImgUrl(activityJpaEntity.getCompositionImgUrl())
                .build();
    }

    public ActivityJpaEntity toJpaEntity(Activity activity) {
        return ActivityJpaEntity.builder()
                .id(activity.getId())
                .fundingAmount(activity.getFundingAmount())
                .targetAmount(activity.getTargetAmount())
                .startedAt(activity.getStartedAt())
                .endedAt(activity.getEndedAt())
                .introduction(activity.getIntroduction())
                .description(activity.getDescription())
                .teaserVideoUrl(activity.getTeaserVideoUrl())
                .revenuePercent(activity.getRevenuePercent())
                .profileImg(activity.getProfileImg())
                .artist(artistMapper.toJpaEntity(activity.getArtist()))
                .albumImgUrl(activity.getAlbumImgUrl())
                .tracklistImgUrl(activity.getTracklistImgUrl())
                .compositionImgUrl(activity.getCompositionImgUrl())
                .build();
    }
}
