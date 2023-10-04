package com.fivengers.blooming.emoji.adapter.out.pesistence.repository;

import com.fivengers.blooming.emoji.adapter.out.pesistence.mapper.EmojiMapper;
import com.fivengers.blooming.emoji.application.port.out.SocketEmojiPort;
import com.fivengers.blooming.emoji.domain.Emoji;
import com.fivengers.blooming.global.exception.live.MotionModelNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SocketEmojiPersistenceAdapter implements SocketEmojiPort {

    private final EmojiQueryRepository emojiQueryRepository;
    private final EmojiMapper emojiMapper;

    @Override
    public Optional<Emoji> findEmojiByMotion(Long motionModelId, String motionName) {
        return emojiQueryRepository.findEmojiByMotionModelAndMotion(motionModelId, motionName)
                .map(emojiMapper::toDomain);
    }

    @Override
    public String findMotionModelUrlByArtist(Long artistId) {
        return emojiQueryRepository.findMotionModelUrlByArtist(artistId)
                .orElse(emojiQueryRepository.findPublicMotionModelUrl()
                        .orElseThrow(MotionModelNotFoundException::new));
    }
}
