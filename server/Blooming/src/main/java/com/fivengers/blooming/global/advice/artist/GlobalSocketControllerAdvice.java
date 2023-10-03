package com.fivengers.blooming.global.advice.artist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fivengers.blooming.config.stomp.SocketAuthUser;
import com.fivengers.blooming.config.stomp.SocketLogger;
import com.fivengers.blooming.global.exception.SocketException;
import com.fivengers.blooming.global.exception.SocketExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalSocketControllerAdvice {
    private static final String AUTH_USER_HEADER = "simpUser";

    private final SimpMessageSendingOperations messagingTemplate;
    private final SocketLogger socketLogger;

    @MessageExceptionHandler(SocketException.class)
    public String handleSocketException(SocketException exception,
            @Header(AUTH_USER_HEADER) SocketAuthUser user) throws JsonProcessingException {
        socketLogger.error(exception);
        messagingTemplate.convertAndSendToUser(user.getName(), "/queue/error",
                exception.getExceptionCode().stringify());
        return exception.getExceptionCode().getMessage();
    }

    @MessageExceptionHandler(Exception.class)
    public String handleException(Exception exception, @Header(AUTH_USER_HEADER) SocketAuthUser user)
            throws JsonProcessingException {
        log.info("Unregistered Exception occurred...");
        log.info("{}", exception.getMessage());
        exception.printStackTrace();
        messagingTemplate.convertAndSendToUser(user.getName(), "/queue/error",
                SocketExceptionCode.SERVER_ERROR.stringify(exception.getMessage()));
        return exception.getMessage();
    }
}
