package com.fivengers.blooming.project.application.port.out;


import com.fivengers.blooming.project.domain.Concert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConcertPort {

    Page<Concert> findAll(Pageable pageable);
    Page<Concert> findAllOngoingProject(Pageable pageable);
}
