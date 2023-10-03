package com.fivengers.blooming.emoji.adapter.in.web.dto;

import com.fivengers.blooming.config.stomp.SocketAuthUser;
import com.fivengers.blooming.emoji.domain.Emoji;
import lombok.Builder;

@Builder
public record EmojiSendResponse(
        Long senderId,
        String senderUserName,
        int emojiCode,
        String emojiName) {

    public static EmojiSendResponse from(SocketAuthUser user, Emoji emoji) {
        return EmojiSendResponse.builder()
                .senderId(user.getMemberId())
                .senderUserName(user.getLiveUserName())
                .emojiCode(emoji.getCode())
                .emojiName(emoji.getName())
                .build();
    }

}
