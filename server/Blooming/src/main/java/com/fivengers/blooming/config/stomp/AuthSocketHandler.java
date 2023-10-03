package com.fivengers.blooming.config.stomp;

import com.fivengers.blooming.config.security.jwt.JwtValidator;
import com.fivengers.blooming.global.exception.stomp.CommandNotFoundFromFrameException;
import com.fivengers.blooming.global.exception.stomp.SessionIdNotFoundFromFrameException;
import com.fivengers.blooming.global.util.Assertion;
import io.jsonwebtoken.Claims;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthSocketHandler implements ChannelInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String SESSION_ID_HEADER = "sessionId";
    private static final String HEADER_PREFIX = "Bearer ";
    private final JwtValidator jwtValidator;
    private final SocketLogger socketLogger;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("preSend working...");
        log.info("{}", message);
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message,
                StompHeaderAccessor.class);

        StompCommand command = accessor.getCommand();
        Assertion.with(command)
                .setValidation(Objects::nonNull)
                .validateOrThrow(CommandNotFoundFromFrameException::new);

        socketLogger.messageInfo(accessor);

        if (isConnectRequest(command)) {
            String token = extractTokenFromAccessor(accessor);
            jwtValidator.validateAccessToken(token);
            Claims claims = jwtValidator.getTokenClaims(token);
            Long memberId = Long.valueOf(claims.get("id", String.class));
            String sessionId = extractSessionIdFromAccessor(accessor);

            SocketAuthUser socketAuthUser = new SocketAuthUser(memberId, sessionId);
            socketLogger.connect(socketAuthUser);

            accessor.setUser(socketAuthUser);
        }

        return ChannelInterceptor.super.preSend(message, channel);
    }

    private String extractSessionIdFromAccessor(StompHeaderAccessor accessor) {
        String sessionId = accessor.getFirstNativeHeader(SESSION_ID_HEADER);
        Assertion.with(sessionId)
                .setValidation(StringUtils::hasText)
                .validateOrThrow(SessionIdNotFoundFromFrameException::new);
        return sessionId;
    }

    private String extractTokenFromAccessor(StompHeaderAccessor accessor) {
        String bearerToken = accessor.getFirstNativeHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            return bearerToken.substring(HEADER_PREFIX.length());
        }
        return null;
    }

    private boolean isConnectRequest(StompCommand command) {
        return command == StompCommand.CONNECT;
    }
}
