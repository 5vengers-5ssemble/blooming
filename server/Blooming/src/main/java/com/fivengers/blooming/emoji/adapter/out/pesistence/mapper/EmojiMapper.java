package com.fivengers.blooming.emoji.adapter.out.pesistence.mapper;

import com.fivengers.blooming.artist.adapter.out.persistence.mapper.ArtistMapper;
import com.fivengers.blooming.emoji.adapter.out.pesistence.entity.EmojiJpaEntity;
import com.fivengers.blooming.emoji.domain.Emoji;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmojiMapper {

    private final ArtistMapper artistMapper;

    public Emoji toDomain(EmojiJpaEntity emojiJpaEntity) {
        return Emoji.builder()
                .id(emojiJpaEntity.getId())
                .name(emojiJpaEntity.getName())
                .code(emojiJpaEntity.getCode())
                .createdAt(emojiJpaEntity.getCreatedAt())
                .modifiedAt(emojiJpaEntity.getModifiedAt())
                .artist(artistMapper.toDomain(emojiJpaEntity.getArtistJpaEntity()))
                .build();
    }

    public EmojiJpaEntity toJpaEntity(Emoji emoji) {
        return EmojiJpaEntity.builder()
                .id(emoji.getId())
                .name(emoji.getName())
                .code(emoji.getCode())
                .artistJpaEntity(artistMapper.toJpaEntity(emoji.getArtist()))
                .build();
    }

}
