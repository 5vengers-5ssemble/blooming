package com.fivengers.blooming.Activity.domain;

import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {

    public Activity toDomain(ActivityJpaEntity activityJpaEntity){
        return new Activity(activityJpaEntity.getId(), activityJpaEntity.getName());
    }

    public ActivityJpaEntity toEntity(Activity activity){
        return new ActivityJpaEntity(activity.getId(), activity.getName());
    }

}
