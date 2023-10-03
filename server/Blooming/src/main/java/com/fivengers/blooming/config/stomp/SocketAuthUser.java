package com.fivengers.blooming.config.stomp;

import java.security.Principal;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SocketAuthUser implements Principal {

    private Long memberId;
    private String sessionId;
    private String liveUserName;

    public SocketAuthUser(Long memberId, String sessionId, String liveUserName) {
        this.memberId = memberId;
        this.sessionId = sessionId;
        this.liveUserName = liveUserName;
    }

    @Override
    public String getName() {
        return liveUserName;
    }
}
