package com.fivengers.blooming.project.application.service;

import com.fivengers.blooming.project.application.port.in.InvestmentOverviewUseCase;
import com.fivengers.blooming.project.application.port.out.InvestmentOverviewPort;
import com.fivengers.blooming.project.domain.InvestmentOverview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvestmentOverviewService implements InvestmentOverviewUseCase {

    private final InvestmentOverviewPort investmentOverviewPort;
    @Override
    public InvestmentOverview search(Long id) {
        return investmentOverviewPort.findById(id);
    }
}
