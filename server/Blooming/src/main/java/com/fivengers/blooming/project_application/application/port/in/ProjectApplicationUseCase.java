package com.fivengers.blooming.project_application.application.port.in;

import com.fivengers.blooming.project_application.domain.ProjectApplication;
import com.fivengers.blooming.project_application.domain.ProjectApplicationState;

public interface ProjectApplicationUseCase {

    void addProjectApplication(ProjectApplication application);

    ProjectApplication searchByMemberIdAndApplicationState(Long memberId,
            ProjectApplicationState state);

}
