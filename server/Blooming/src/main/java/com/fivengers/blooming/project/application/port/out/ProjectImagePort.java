package com.fivengers.blooming.project.application.port.out;


import com.fivengers.blooming.project.domain.ProjectImage;
import java.util.List;

public interface ProjectImagePort {

    List<ProjectImage> findAllByProjectId(Long id);
}
