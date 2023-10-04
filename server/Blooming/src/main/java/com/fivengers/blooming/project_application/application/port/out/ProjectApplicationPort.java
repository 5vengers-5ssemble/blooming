package com.fivengers.blooming.project_application.application.port.out;

import com.fivengers.blooming.project_application.domain.ProjectApplication;

public interface ProjectApplicationPort {

    void save(ProjectApplication application);
}
