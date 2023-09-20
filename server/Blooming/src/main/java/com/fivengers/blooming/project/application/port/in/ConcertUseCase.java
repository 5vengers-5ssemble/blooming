package com.fivengers.blooming.project.application.port.in;

import com.fivengers.blooming.project.domain.Concert;

import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ConcertUseCase {

    List<Concert> searchAll(Pageable pageable);
    List<Concert> searchAllOngoingProject(Pageable pageable);
}
