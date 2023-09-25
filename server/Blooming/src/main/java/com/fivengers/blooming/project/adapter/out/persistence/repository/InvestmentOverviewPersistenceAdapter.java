package com.fivengers.blooming.project.adapter.out.persistence.repository;

import com.fivengers.blooming.global.exception.project.ProjectNotFoundException;
import com.fivengers.blooming.project.adapter.out.persistence.mapper.InvestmentOverviewMapper;
import com.fivengers.blooming.project.application.port.out.InvestmentOverviewPort;
import com.fivengers.blooming.project.domain.InvestmentOverview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InvestmentOverviewPersistenceAdapter implements InvestmentOverviewPort {

    private final InvestmentOverviewMapper overviewMapper;
    private final InvestmentOverviewSpringDataRepository overviewSpringDataRepository;

    @Override
    public InvestmentOverview findById(Long id) {
        return overviewSpringDataRepository.findById(id)
                .map(overviewMapper::toDomain)
                .orElseThrow(ProjectNotFoundException::new);
    }
}
