package com.fivengers.blooming.emoji.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Motion {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private MotionModel motionModel;
    private Emoji emoji;

    @Builder
    public Motion(Long id, String name, LocalDateTime createdAt, LocalDateTime modifiedAt,
            MotionModel motionModel, Emoji emoji) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.motionModel = motionModel;
        this.emoji = emoji;
    }
}
