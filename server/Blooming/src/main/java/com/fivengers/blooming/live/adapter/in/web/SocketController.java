package com.fivengers.blooming.live.adapter.in.web;

import com.fivengers.blooming.live.adapter.in.web.dto.websocket.EmojiSendRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;


@Slf4j
@Controller
@RequiredArgsConstructor
public class SocketController {

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/lives/emoji")
    public void sendEmoji(@Payload EmojiSendRequest emojiSendRequest,
            @DestinationVariable("liveId") Long liveId) {
        messagingTemplate.convertAndSend("/topic/lives/"+liveId+"/emoji", emojiSendRequest);
    }

}
