package com.fivengers.blooming.project.application.service;


import com.fivengers.blooming.project.application.port.in.ProjectImageUseCase;
import com.fivengers.blooming.project.application.port.out.ProjectImagePort;
import com.fivengers.blooming.project.domain.ProjectImage;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectImageService implements ProjectImageUseCase {

    private final ProjectImagePort projectImagePort;

    @Override
    public List<ProjectImage> findAllByProjectId(Long id) {
        return projectImagePort.findAllByProjectId(id);
    }
}
