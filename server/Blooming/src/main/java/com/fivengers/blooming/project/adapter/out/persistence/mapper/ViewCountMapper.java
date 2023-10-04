package com.fivengers.blooming.project.adapter.out.persistence.mapper;

import com.fivengers.blooming.project.adapter.out.persistence.entity.ViewCountJpaEntity;
import com.fivengers.blooming.project.domain.ViewCount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViewCountMapper {

    private final ProjectMapper projectMapper;

    public ViewCount toDomain(ViewCountJpaEntity viewCount) {
        return ViewCount.builder()
                .id(viewCount.getId())
                .viewCount(viewCount.getViewCount())
                .createdAt(viewCount.getCreatedAt())
                .modifiedAt(viewCount.getModifiedAt())
                .project(projectMapper.toDomain(viewCount.getProject()))
                .build();
    }

    public ViewCountJpaEntity toJpaEntity(ViewCount viewCount) {
        return ViewCountJpaEntity.builder()
                .id(viewCount.getId())
                .viewCount(viewCount.getViewCount())
                .project(projectMapper.toJpaEntity(viewCount.getProject()))
                .build();
    }
}
