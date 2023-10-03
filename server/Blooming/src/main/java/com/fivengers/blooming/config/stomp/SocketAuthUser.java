package com.fivengers.blooming.config.stomp;

import java.security.Principal;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SocketAuthUser implements Principal {

    private Long memberId;
    private String sessionId;

    public SocketAuthUser(Long memberId, String sessionId) {
        this.memberId = memberId;
        this.sessionId = sessionId;
    }

    @Override
    public String getName() {
        return sessionId;
    }
}
