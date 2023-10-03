package com.fivengers.blooming.emoji.adapter.in.web;

import com.fivengers.blooming.config.stomp.SocketAuthUser;
import com.fivengers.blooming.config.stomp.SocketLogger;
import com.fivengers.blooming.emoji.adapter.in.web.dto.EmojiSendRequest;
import com.fivengers.blooming.emoji.adapter.in.web.dto.EmojiSendResponse;
import com.fivengers.blooming.emoji.application.port.in.SocketEmojiUseCase;
import com.fivengers.blooming.emoji.domain.Emoji;
import com.fivengers.blooming.global.exception.SocketException;
import com.fivengers.blooming.global.exception.SocketExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;


@Slf4j
@Controller
@RequiredArgsConstructor
public class SocketController {

    private static final String AUTH_USER_HEADER = "simpUser";

    private final SimpMessageSendingOperations messagingTemplate;
    private final SocketEmojiUseCase socketEmojiUseCase;
    private final SocketLogger socketLogger;

    @MessageMapping("/lives/emoji")
    public String sendEmoji(@Header(AUTH_USER_HEADER) SocketAuthUser user,
            @Payload EmojiSendRequest emojiSendRequest) {
        socketLogger.controller("sendEmoji");
        log.info("request : {}", emojiSendRequest);

        String sessionId = user.getSessionId();
        // TODO 1: 파라미터 입력값 검증 어노테이션 추가 예정
        // TODO 2: memberId로 해당 라이브에 참여한 유저인지 검증 서비스 추후 구현 예정
        Emoji emoji = socketEmojiUseCase.searchEmojiByMotion(emojiSendRequest);
        log.info("searched Emoji : {}", emoji);

        messagingTemplate.convertAndSend(
                "/topic/lives/" + sessionId + "/emoji",
                EmojiSendResponse.from(user, emoji));

        return emoji.toString();
    }

}
