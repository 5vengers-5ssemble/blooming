package com.fivengers.blooming.project.domain;

import com.fivengers.blooming.artist.domain.Artist;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Concert extends Project {

    @Builder
    public Concert(Long id,
            String name,
            Long fundingAmount,
            Long targetAmount,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String description,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            Artist artist) {
        super(id, name, fundingAmount, targetAmount, startedAt, endedAt,
                description, createdAt, modifiedAt, artist);
    }
}
