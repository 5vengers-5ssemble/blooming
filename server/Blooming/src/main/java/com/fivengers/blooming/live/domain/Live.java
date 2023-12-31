package com.fivengers.blooming.live.domain;

import com.fivengers.blooming.artist.domain.Artist;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class Live {

    private static final String SESSION_PREFIX = "blooming";

    private Long id;
    private String title;
    private String thumbnailUrl;
    private String motionModelUrl;
    private LocalDateTime createdAt;
    private LocalDateTime endedAt;
    private LocalDateTime modifiedAt;
    private Artist artist;
    private int numberOfViewers;

    @Builder
    public Live(
            Long id,
            String title,
            String thumbnailUrl,
            String motionModelUrl,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            LocalDateTime endedAt,
            Artist artist,
            int numberOfViewers) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.motionModelUrl = motionModelUrl;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.endedAt = endedAt;
        this.artist = artist;
        this.numberOfViewers = numberOfViewers;
    }

    public String getSessionId() {
        return SESSION_PREFIX + id;
    }

    public void setNumberOfViewers(int numberOfViewers) {
        this.numberOfViewers = numberOfViewers;
    }

    public void setMotionModelUrl(String motionModelUrl) {
        this.motionModelUrl = motionModelUrl;
    }

    public boolean canCloseLive(Long memberId) {
        return isStreamer(memberId);
    }

    public String getLiveUserName(Long memberId) {
        if (isStreamer(memberId)) {
            return artist.getStageName();
        }
        return artist.getMember().getNickname();
    }

    private boolean isStreamer(Long memberId) {
        return Objects.equals(artist.getMember().getId(), memberId);
    }

    public void close() {
        endedAt = LocalDateTime.now();
    }
}
