package com.fivengers.blooming.project.domain;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ViewCount {

    private Long id;
    private Long viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Project project;

    @Builder
    public ViewCount(Long id, Long viewCount, LocalDateTime createdAt, LocalDateTime modifiedAt,
            Project project) {
        this.id = id;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.project = project;
    }
}
