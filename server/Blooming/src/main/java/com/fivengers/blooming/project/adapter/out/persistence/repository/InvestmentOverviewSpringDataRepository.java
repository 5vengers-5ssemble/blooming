package com.fivengers.blooming.project.adapter.out.persistence.repository;

import com.fivengers.blooming.project.adapter.out.persistence.entity.InvestmentOverviewJpaEntity;
import com.fivengers.blooming.project.domain.InvestmentOverview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentOverviewSpringDataRepository extends JpaRepository<InvestmentOverviewJpaEntity, Long> {

}
