package com.fivengers.blooming.live.application.port.in;

import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.live.adapter.in.web.dto.LiveCreateRequest;
import com.fivengers.blooming.live.domain.Live;
import com.fivengers.blooming.member.domain.Member;

public interface LiveArtistUseCase {

    Live createLive(LiveCreateRequest liveCreateRequest);

    Live closeLive(Long liveId, Member member);

}
