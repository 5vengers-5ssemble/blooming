package com.fivengers.blooming.live.application.port.in;

import com.fivengers.blooming.live.adapter.in.web.dto.LiveCreateRequest;
import com.fivengers.blooming.live.domain.Live;

public interface LiveArtistUseCase {

    Live createLive(LiveCreateRequest liveCreateRequest);

}
