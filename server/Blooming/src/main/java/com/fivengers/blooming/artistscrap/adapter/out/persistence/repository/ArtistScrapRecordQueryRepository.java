package com.fivengers.blooming.artistscrap.adapter.out.persistence.repository;

import static com.fivengers.blooming.artistscrap.adapter.out.persistence.entity.QArtistScrapRecordJpaEntity.artistScrapRecordJpaEntity;

import com.fivengers.blooming.artistscrap.adapter.out.persistence.entity.ArtistScrapRecordJpaEntity;
import com.fivengers.blooming.artistscrap.adapter.out.persistence.entity.QArtistScrapRecordJpaEntity;
import com.fivengers.blooming.global.support.QuerydslRepositorySupport;
import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistScrapRecordQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ArtistScrapRecordQueryRepository(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<ArtistScrapRecordJpaEntity> findByArtistIdOrderByStartDateDescLimit(Long artistId,
                                                                                    Long limit) {
        return queryFactory.selectFrom(artistScrapRecordJpaEntity)
                .where(artistScrapRecordJpaEntity.artistJpaEntity.id.eq(artistId))
                .orderBy(artistScrapRecordJpaEntity.startDateOnWeek.desc())
                .limit(limit)
                .fetch();
    }


}
