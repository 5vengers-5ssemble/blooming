package com.fivengers.blooming.artist.adapter.in.web.dto;

import com.fivengers.blooming.project.domain.Project;

public record ArtistProjectListResponse(Long id,
                                        String thumbnail) {

    public static ArtistProjectListResponse from(Project project) {

        return new ArtistProjectListResponse(project.getId(), project.getProfileImg());
    }
}
