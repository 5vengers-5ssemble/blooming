package com.fivengers.blooming.project.application.port.out;

import com.fivengers.blooming.project.domain.InvestmentOverview;
import com.fivengers.blooming.project.domain.Project;
import org.springframework.stereotype.Component;

public interface InvestmentOverviewPort {

    InvestmentOverview findById(Long id);
}
