package com.fivengers.blooming.artistscrap.adapter.out.persistence.repository;

import static com.fivengers.blooming.artistscrap.adapter.out.persistence.entity.QArtistScrapJpaEntity.artistScrapJpaEntity;

import com.fivengers.blooming.artistscrap.adapter.out.persistence.entity.ArtistScrapJpaEntity;
import com.fivengers.blooming.artistscrap.adapter.out.persistence.entity.QArtistScrapJpaEntity;
import com.fivengers.blooming.global.support.QuerydslRepositorySupport;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistScrapQueryRepository extends QuerydslRepositorySupport {

    public ArtistScrapQueryRepository() {
        super(ArtistScrapJpaEntity.class);
    }

    public List<ArtistScrapJpaEntity> findByMemberId(Long memberId) {
        return selectFrom(artistScrapJpaEntity)
                .leftJoin(artistScrapJpaEntity.artistJpaEntity).fetchJoin()
                .leftJoin(artistScrapJpaEntity.memberJpaEntity).fetchJoin()
                .where(artistScrapJpaEntity.memberJpaEntity.id.eq(memberId))
                .fetch();
    }
}
