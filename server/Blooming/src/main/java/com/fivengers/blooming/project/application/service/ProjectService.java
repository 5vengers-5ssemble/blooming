package com.fivengers.blooming.project.application.service;


import com.fivengers.blooming.project.application.port.in.ProjectUseCase;
import com.fivengers.blooming.project.application.port.out.ProjectPort;
import com.fivengers.blooming.project.domain.Project;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService implements ProjectUseCase {

private final ProjectPort projectPort;

    @Override
    public List<Project> searchProjectsById(Long artistId) {
        return projectPort.findAllByArtistId(artistId);
    }
}
