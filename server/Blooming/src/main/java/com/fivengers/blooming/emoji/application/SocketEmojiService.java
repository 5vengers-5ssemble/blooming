package com.fivengers.blooming.emoji.application;

import com.fivengers.blooming.emoji.adapter.in.web.dto.EmojiSendRequest;
import com.fivengers.blooming.emoji.application.port.in.SocketEmojiUseCase;
import com.fivengers.blooming.emoji.application.port.out.SocketEmojiPort;
import com.fivengers.blooming.emoji.domain.Emoji;
import com.fivengers.blooming.global.exception.emoji.EmojiNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocketEmojiService implements SocketEmojiUseCase {

    private final SocketEmojiPort socketEmojiPort;
    @Override
    public Emoji searchEmojiByMotion(EmojiSendRequest emojiSendRequest) {
        return socketEmojiPort.findEmojiByMotion(emojiSendRequest.motionModelId(),
                emojiSendRequest.motionName()).orElseThrow(EmojiNotFoundException::new);
    }
}
