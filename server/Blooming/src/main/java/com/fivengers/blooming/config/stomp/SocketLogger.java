package com.fivengers.blooming.config.stomp;

import com.fivengers.blooming.global.exception.SocketException;
import com.fivengers.blooming.global.exception.SocketExceptionCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@NoArgsConstructor
public class SocketLogger {
    public void messageInfo(StompHeaderAccessor accessor) {
        StompCommand command = accessor.getCommand();
        String sessionId = accessor.getSessionId();
        String destination = accessor.getDestination();

        log.info("[Command] " + command + "\n[URL] " + destination + "\n[Session] " + sessionId);
    }

    public void connect(SocketAuthUser socketAuthUser) {
        log.info("[CONNECT] " + socketAuthUser);
    }

    public void error(SocketException exception) {
        log.info("[SOCKET EXCEPTION] {} : {}",
                exception.getExceptionCode().getErrorCode(),
                exception.getExceptionCode().getMessage());
    }
}
