package com.fivengers.blooming.project.application.port.in;

import com.fivengers.blooming.project.domain.Project;
import java.util.List;

public interface ProjectUseCase {

    List<Project> searchProjectsById(Long artistId);

    List<Project> searchAdvertisingProjects();
}
