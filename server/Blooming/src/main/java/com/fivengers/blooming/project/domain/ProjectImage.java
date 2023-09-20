package com.fivengers.blooming.project.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectImage {

    private Long id;
    private String imageUrl;
    private Project project;

    @Builder
    public ProjectImage(Long id, String imageUrl, Project project) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.project = project;
    }
}
