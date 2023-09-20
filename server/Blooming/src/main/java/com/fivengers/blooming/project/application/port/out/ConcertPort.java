package com.fivengers.blooming.project.application.port.out;


import com.fivengers.blooming.project.domain.Concert;

import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ConcertPort {

    List<Concert> findAll(Pageable pageable);
    List<Concert> findAllOngoingProject(Pageable pageable);
}
