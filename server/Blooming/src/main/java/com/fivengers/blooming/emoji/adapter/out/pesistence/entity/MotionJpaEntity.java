package com.fivengers.blooming.emoji.adapter.out.pesistence.entity;

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
@Table(name = "motion")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MotionJpaEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "motion_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean deleted;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motion_model_id", nullable = false)
    private MotionModelJpaEntity motionModelJpaEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emoji_id", nullable = true)
    private EmojiJpaEntity emojiJpaEntity;

    @Builder
    public MotionJpaEntity(Long id, String name, MotionModelJpaEntity motionModelJpaEntity,
            EmojiJpaEntity emojiJpaEntity) {
        this.id = id;
        this.name = name;
        this.motionModelJpaEntity = motionModelJpaEntity;
        this.emojiJpaEntity = emojiJpaEntity;
    }
}
