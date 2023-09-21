package com.fivengers.blooming.project.application.port.in;

import com.fivengers.blooming.project.domain.InvestmentOverview;

public interface InvestmentOverviewUseCase {

    InvestmentOverview search(Long id);
}
