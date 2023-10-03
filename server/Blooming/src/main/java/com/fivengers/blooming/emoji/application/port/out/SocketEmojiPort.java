package com.fivengers.blooming.emoji.application.port.out;

import com.fivengers.blooming.emoji.domain.Emoji;
import java.util.Optional;

public interface SocketEmojiPort {

    Optional<Emoji> findEmojiByMotion(Long motionModelId, String motionName);
}
