package com.fivengers.blooming.project_application.application;


import com.fivengers.blooming.global.exception.project.ProjectNotFoundException;
import com.fivengers.blooming.project_application.application.port.in.ProjectApplicationUseCase;
import com.fivengers.blooming.project_application.application.port.out.ProjectApplicationPort;
import com.fivengers.blooming.project_application.domain.ProjectApplication;
import com.fivengers.blooming.project_application.domain.ProjectApplicationState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectApplicationService implements ProjectApplicationUseCase {

    private final ProjectApplicationPort projectApplicationPort;

    @Override
    public void addProjectApplication(ProjectApplication application) {
        projectApplicationPort.save(application);
    }

    @Override
    public ProjectApplication searchByMemberIdAndApplicationState(Long memberId,
            ProjectApplicationState state) {
        return projectApplicationPort.findByMemberIdAndApplicationState(memberId, state).orElseThrow(
                ProjectNotFoundException::new);
    }


}
