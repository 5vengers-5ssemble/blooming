package com.fivengers.blooming.project.application.port.in;

import com.fivengers.blooming.project.domain.Project;
import com.fivengers.blooming.project.domain.ViewCount;
import java.util.List;

public interface ViewCountUseCase {
    List<ViewCount> searchWeeklyViewCount(Project project);
}
