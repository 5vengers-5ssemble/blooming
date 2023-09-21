package com.fivengers.blooming.live.application.port.out;

import com.fivengers.blooming.live.domain.Live;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LivePort {
    Page<Live> findByKeyword(String keyword, Pageable pageable);
    Page<Live> findByArtistStageName(String keyword, Pageable pageable);
}
