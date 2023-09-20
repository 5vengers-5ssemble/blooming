package com.fivengers.blooming.activity.adapter.out.persistence.mapper;

import com.fivengers.blooming.activity.adapter.out.persistence.entity.ActivityJpaEntity;
import com.fivengers.blooming.activity.domain.Activity;
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
