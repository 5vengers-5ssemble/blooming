package com.fivengers.blooming.project.application.port.out;

import com.fivengers.blooming.project.domain.ViewCount;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ViewCountPort {

    List<ViewCount> findAll(Pageable pageable);
}
