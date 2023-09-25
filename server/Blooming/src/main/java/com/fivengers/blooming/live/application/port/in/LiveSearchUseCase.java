package com.fivengers.blooming.live.application.port.in;

import com.fivengers.blooming.live.domain.Live;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LiveSearchUseCase {

    Page<Live> searchByKeyword(String query, Pageable pageable);

    Page<Live> searchByArtist(String query, Pageable pageable);
}
