package com.fivengers.blooming.project_application.application.port.out;

import com.fivengers.blooming.project_application.domain.ProjectApplication;
import com.fivengers.blooming.project_application.domain.ProjectApplicationState;
import java.util.Optional;

public interface ProjectApplicationPort {

    void save(ProjectApplication application);

    Optional<ProjectApplication> findByMemberIdAndApplicationState(Long memberId,
            ProjectApplicationState state);
}
