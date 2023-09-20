package com.fivengers.blooming.project.application.port.in;

import com.fivengers.blooming.project.domain.Concert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConcertUseCase {

    Page<Concert> searchAll(Pageable pageable);
    Page<Concert> searchAllOngoingProject(Pageable pageable);
}
