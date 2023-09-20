package com.fivengers.blooming.fixture.Payment.adapter.out.persistence;

import com.fivengers.blooming.activity.domain.Activity;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class FakeActivityPersistenceJpaAdapter {

    private final Map<Long, Activity> store = new HashMap<>();
    private Long autoIncrementId = 1L;

    public Activity save(Activity activity) {
        if (isPersistenceObject(activity)) {
            store.put(activity.getId(), activity);
            return activity;
        }
        return persist(activity);
    }

    private boolean isPersistenceObject(Activity activity) {
        return activity.getId() != null;
    }

    private Activity persist(Activity activity) {
        LocalDateTime now = LocalDateTime.now();
        Activity persistedActivity = Activity.builder()
                .id(autoIncrementId)
                .name(activity.getName())
                .build();
        store.put(autoIncrementId, persistedActivity);
        autoIncrementId++;
        return persistedActivity;
    }
}
