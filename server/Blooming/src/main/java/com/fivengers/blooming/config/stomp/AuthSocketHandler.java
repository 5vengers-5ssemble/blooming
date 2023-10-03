package com.fivengers.blooming.config.stomp;

import com.fivengers.blooming.config.security.jwt.JwtValidator;
import com.fivengers.blooming.global.exception.SocketExceptionCode;
import com.fivengers.blooming.global.exception.stomp.CommandNotFoundFromFrameException;
import com.fivengers.blooming.global.exception.stomp.RequiredHeaderMissingFromFrameException;
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
    private static final String LIVE_USER_NAME_HEADER = "liveUserName";
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
            SocketAuthUser socketAuthUser = createSocketAuthUserFromFrame(accessor);
            socketLogger.connect(socketAuthUser);

            accessor.setUser(socketAuthUser);
        }

        return ChannelInterceptor.super.preSend(message, channel);
    }

    private SocketAuthUser createSocketAuthUserFromFrame(StompHeaderAccessor accessor) {
        Long memberId = extractMemberIdFromAccessor(accessor);
        String sessionId = extractAttributeFromAccessor(accessor, SESSION_ID_HEADER,
                SocketExceptionCode.SESSION_ID_NOT_FOUND);
        String liveUserName = extractAttributeFromAccessor(accessor, LIVE_USER_NAME_HEADER,
                SocketExceptionCode.LIVE_USER_NAME_NOT_FOUND);
        return new SocketAuthUser(memberId, sessionId, liveUserName);
    }

    private Long extractMemberIdFromAccessor(StompHeaderAccessor accessor) {
        String token = extractTokenFromAccessor(accessor);
        jwtValidator.validateAccessToken(token);
        Claims claims = jwtValidator.getTokenClaims(token);
        return Long.valueOf(claims.get("id", String.class));
    }

    private String extractTokenFromAccessor(StompHeaderAccessor accessor) {
        String bearerToken = accessor.getFirstNativeHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            return bearerToken.substring(HEADER_PREFIX.length());
        }
        return null;
    }

    private String extractAttributeFromAccessor(StompHeaderAccessor accessor,
            String attributeHeader, SocketExceptionCode exceptionCode) {
        String attribute = accessor.getFirstNativeHeader(attributeHeader);
        Assertion.with(attribute)
                .setValidation(StringUtils::hasText)
                .validateOrThrow(() -> new RequiredHeaderMissingFromFrameException(exceptionCode));
        return attribute;
    }

    private boolean isConnectRequest(StompCommand command) {
        return command == StompCommand.CONNECT;
    }
}
