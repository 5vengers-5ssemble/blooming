package com.fivengers.blooming.project_application.application.port.out;

import com.fivengers.blooming.project_application.domain.ProjectApplication;
import java.util.Optional;

public interface ProjectApplicationPort {

    void save(ProjectApplication application);

    Optional<ProjectApplication> findByMemberId(Long memberId);
}
