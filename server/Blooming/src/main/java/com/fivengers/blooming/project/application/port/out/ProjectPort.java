package com.fivengers.blooming.project.application.port.out;

import com.fivengers.blooming.project.domain.Project;
import java.util.List;

public interface ProjectPort {

    List<Project> findAllByArtistId(Long artistId);
}
