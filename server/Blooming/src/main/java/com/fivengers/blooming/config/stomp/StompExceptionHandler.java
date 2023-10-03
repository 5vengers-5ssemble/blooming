package com.fivengers.blooming.config.stomp;

import com.fivengers.blooming.global.exception.SocketException;
import com.fivengers.blooming.global.exception.SocketExceptionCode;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

@Slf4j
@Component
public class StompExceptionHandler extends StompSubProtocolErrorHandler {

    private final SocketLogger socketLogger;

    public StompExceptionHandler(SocketLogger socketLogger) {
        super();
        this.socketLogger = socketLogger;
    }

    @Override
    public Message<byte[]> handleClientMessageProcessingError(Message<byte[]> clientMessage,
            Throwable ex) {
        log.info("[SOCKET LOGGER] socket error occurred!!");

        Throwable exception = converterTrowException(ex);

        if (exception instanceof SocketException) {
            socketLogger.error((SocketException) exception);
            return prepareErrorMessage(clientMessage, ((SocketException) exception).getExceptionCode());
        }
        log.info("[SOCKET LOGGER] not registered SocketException...");
        exception.printStackTrace();
        log.info("What is Cause?");
        exception.getCause().printStackTrace();
        return super.handleClientMessageProcessingError(clientMessage, exception);

    }

    private Throwable converterTrowException(final Throwable exception) {
        if (exception instanceof MessageDeliveryException) {
            return exception.getCause();
        }
        return exception;
    }

    private Message<byte[]> prepareErrorMessage(final Message<byte[]> clientMessage,
            SocketExceptionCode exceptionCode) {

        final StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.ERROR);
        accessor.setMessage(exceptionCode.getErrorCode());
        accessor.setLeaveMutable(true);

        return MessageBuilder.createMessage(
                exceptionCode.stringify().getBytes(StandardCharsets.UTF_8),
                accessor.getMessageHeaders()
        );
    }

    @Override
    protected Message<byte[]> handleInternal(StompHeaderAccessor errorHeaderAccessor,
            byte[] errorPayload, Throwable cause, StompHeaderAccessor clientHeaderAccessor) {

        return MessageBuilder.createMessage(errorPayload, errorHeaderAccessor.getMessageHeaders());

//        return super.handleInternal(errorHeaderAccessor, errorPayload, cause, clientHeaderAccessor);
    }
}
