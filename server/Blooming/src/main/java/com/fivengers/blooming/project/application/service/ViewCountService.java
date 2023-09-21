package com.fivengers.blooming.project.application.service;

import com.fivengers.blooming.project.application.port.in.ViewCountUseCase;
import com.fivengers.blooming.project.application.port.out.ViewCountPort;
import com.fivengers.blooming.project.domain.ViewCount;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewCountService implements ViewCountUseCase {

    private final ViewCountPort viewCountPort;

    @Override
    public List<ViewCount> searchWeeklyViewCount() {
        return viewCountPort.findAll(
                PageRequest.of(0,
                        7,
                        Sort.by("createdAt").descending()));
    }
}
