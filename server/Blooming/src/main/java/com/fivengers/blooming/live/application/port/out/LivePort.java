package com.fivengers.blooming.live.application.port.out;

import com.fivengers.blooming.live.domain.Live;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface LivePort {
    List<Live> findByKeyword(String keyword, Pageable pageable);
}
