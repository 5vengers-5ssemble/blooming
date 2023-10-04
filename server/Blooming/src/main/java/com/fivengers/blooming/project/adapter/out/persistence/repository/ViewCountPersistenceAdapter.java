package com.fivengers.blooming.project.adapter.out.persistence.repository;

import com.fivengers.blooming.project.adapter.out.persistence.mapper.ProjectMapper;
import com.fivengers.blooming.project.adapter.out.persistence.mapper.ViewCountMapper;
import com.fivengers.blooming.project.application.port.out.ViewCountPort;
import com.fivengers.blooming.project.domain.Project;
import com.fivengers.blooming.project.domain.ViewCount;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ViewCountPersistenceAdapter implements ViewCountPort {

    private final ViewCountMapper viewCountMapper;
    private final ProjectMapper projectMapper;
    private final ViewCountSpringDataRepository viewCountSpringDataRepository;
    @Override
    public List<ViewCount> findAllByProject(Project project, Pageable pageable) {
        return viewCountSpringDataRepository.findAllByProject(
                projectMapper.toJpaEntity(project), pageable).stream()
                .map(viewCountMapper::toDomain)
                .toList();
    }
}
