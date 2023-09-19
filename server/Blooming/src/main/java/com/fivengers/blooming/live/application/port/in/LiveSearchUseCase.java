package com.fivengers.blooming.live.application.port.in;

import com.fivengers.blooming.live.adapter.in.web.dto.SearchRequest;
import com.fivengers.blooming.live.domain.Live;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface LiveSearchUseCase {

    List<Live> searchByKeyword(String query, Pageable pageable);
}