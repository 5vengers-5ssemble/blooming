package com.fivengers.blooming.emoji.adapter.out.pesistence.repository;

import com.fivengers.blooming.emoji.adapter.out.pesistence.entity.EmojiJpaEntity;
import com.fivengers.blooming.emoji.adapter.out.pesistence.entity.QEmojiJpaEntity;
import com.fivengers.blooming.emoji.adapter.out.pesistence.entity.QMotionJpaEntity;
import com.fivengers.blooming.emoji.adapter.out.pesistence.entity.QMotionModelJpaEntity;
import com.fivengers.blooming.global.support.QuerydslRepositorySupport;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class EmojiQueryRepository extends QuerydslRepositorySupport {
    private final QMotionModelJpaEntity motionModel = QMotionModelJpaEntity.motionModelJpaEntity;
    private final QMotionJpaEntity motion = QMotionJpaEntity.motionJpaEntity;
    private final QEmojiJpaEntity emoji = QEmojiJpaEntity.emojiJpaEntity;

    public EmojiQueryRepository() {
        super(EmojiJpaEntity.class);
    }

    public Optional<EmojiJpaEntity> findEmojiByMotionModelAndMotion(Long motionModelId, String motionName) {
        return Optional.ofNullable(
                selectFrom(emoji)
                .where(emoji.id.eq(findEmojiIdByMotionModelAndMotion(motionModelId, motionName)))
                .fetchOne());
    }

    private JPQLQuery<Long> findEmojiIdByMotionModelAndMotion(Long motionModelId, String motionName) {
        return JPAExpressions.select(motion.emojiJpaEntity.id)
                .from(motion)
                .where(motion.motionModelJpaEntity.id.eq(motionModelId)
                        .and(motion.name.eq(motionName))
                        .and(motion.deleted.isFalse()));
    }
}
