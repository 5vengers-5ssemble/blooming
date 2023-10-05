package com.fivengers.blooming.artist.adapter.in.web.dto;

import com.fivengers.blooming.project.domain.Project;
import com.fivengers.blooming.project.domain.ProjectType;

public record ArtistProjectListResponse(Long id,
                                        String thumbnail,
                                        ProjectType type) {

    public static ArtistProjectListResponse from(Project project) {

        return new ArtistProjectListResponse(
                project.getId(), project.getProfileImg(), project.getDtype());
    }
}
