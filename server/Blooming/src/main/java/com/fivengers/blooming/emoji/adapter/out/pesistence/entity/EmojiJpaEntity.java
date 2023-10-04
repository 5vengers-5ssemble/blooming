package com.fivengers.blooming.emoji.adapter.out.pesistence.entity;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.ArtistJpaEntity;
import com.fivengers.blooming.global.audit.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "emoji")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmojiJpaEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emoji_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer code;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = true)
    private ArtistJpaEntity artistJpaEntity;

    @Builder
    public EmojiJpaEntity(Long id, String name, Integer code, Boolean deleted,
            ArtistJpaEntity artistJpaEntity) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.deleted = deleted;
        this.artistJpaEntity = artistJpaEntity;
    }
}
