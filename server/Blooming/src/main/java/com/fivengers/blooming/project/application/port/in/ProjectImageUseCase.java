package com.fivengers.blooming.project.application.port.in;

import com.fivengers.blooming.project.domain.ProjectImage;
import java.util.List;

/**
 * 사용하지 않음
 */
public interface ProjectImageUseCase {

    List<ProjectImage> findAllByProjectId(Long id);
}
