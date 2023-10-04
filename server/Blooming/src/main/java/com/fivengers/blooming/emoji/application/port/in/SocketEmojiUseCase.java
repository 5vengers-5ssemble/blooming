package com.fivengers.blooming.emoji.application.port.in;

import com.fivengers.blooming.emoji.adapter.in.web.dto.EmojiSendRequest;
import com.fivengers.blooming.emoji.domain.Emoji;

public interface SocketEmojiUseCase {

    Emoji searchEmojiByMotion(EmojiSendRequest emojiSendRequest);
}
