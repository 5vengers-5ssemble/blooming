package com.fivengers.blooming.live.adapter.out.persistence.repository;

import com.fivengers.blooming.artist.adapter.out.persistence.entity.QArtistJpaEntity;
import com.fivengers.blooming.global.exception.global.InvalidSortOrderException;
import com.fivengers.blooming.global.support.QuerydslRepositorySupport;
import com.fivengers.blooming.live.adapter.out.persistence.entity.LiveJpaEntity;
import com.fivengers.blooming.live.adapter.out.persistence.entity.QLiveJpaEntity;
import com.fivengers.blooming.member.adapter.out.persistence.entity.QMemberJpaEntity;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.function.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class LiveQueryRepository extends QuerydslRepositorySupport {
    private final QMemberJpaEntity member = QMemberJpaEntity.memberJpaEntity;
    private final QArtistJpaEntity artist = QArtistJpaEntity.artistJpaEntity;
    private final QLiveJpaEntity live = QLiveJpaEntity.liveJpaEntity;

    public LiveQueryRepository(EntityManager em) {
        super(LiveJpaEntity.class);
    }

    Page<LiveJpaEntity> findActiveLive(Pageable pageable) {
        return applyPagination(
                pageable,
                this::findActiveLiveWithActiveMember,
                this::findActiveLiveCountWithActiveMember
        );
    }

    Page<LiveJpaEntity> findActiveLiveByTitleKeyword(String keyword, Pageable pageable) {

        List<OrderSpecifier> orders = getAllOrderSpecifiers(pageable);

        List<LiveJpaEntity> lives = findLiveWithActiveMember()
                .where(live.title.contains(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orders.toArray(OrderSpecifier[]::new))
                .fetch();

        Long count = findLiveCountWithActiveMember();
        return new PageImpl<>(lives, pageable, count);
    }

    Page<LiveJpaEntity> findActiveLiveByArtist(String keyword, Pageable pageable) {

        List<OrderSpecifier> orders = getAllOrderSpecifiers(pageable);

        List<LiveJpaEntity> lives = findLiveWithActiveMember()
                .where(artist.stageName.contains(keyword))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(orders.toArray(OrderSpecifier[]::new))
                .fetch();

        Long count = findLiveCountWithActiveMember();
        return new PageImpl<>(lives, pageable, count);
    }

    private JPAQuery<LiveJpaEntity> findActiveLiveWithActiveMember(JPAQueryFactory query) {
        return query.selectFrom(live)
                .leftJoin(live.artistJpaEntity, artist)
                .fetchJoin()
                .leftJoin(artist.memberJpaEntity, member)
                .fetchJoin()
                .where(isActiveArtist().and(isActiveLive()));
    }

    private JPAQuery<LiveJpaEntity> findLiveWithActiveMember() {
        return selectFrom(live)
                .leftJoin(live.artistJpaEntity, artist)
                .fetchJoin()
                .leftJoin(artist.memberJpaEntity, member)
                .fetchJoin()
                .where(isActiveArtist().and(isActiveLive()));
    }

    private JPAQuery<Long> findActiveLiveCountWithActiveMember(JPAQueryFactory query) {
        return query.select(live.count())
                .from(live)
                .leftJoin(live.artistJpaEntity, artist)
                .leftJoin(artist.memberJpaEntity, member)
                .where(isActiveArtist().and(isActiveLive()));
    }

    private Long findLiveCountWithActiveMember() {
        return select(live.count())
                .from(live)
                .leftJoin(live.artistJpaEntity, artist)
                .leftJoin(artist.memberJpaEntity, member)
                .where(isActiveArtist().and(isActiveLive()))
                .fetchOne();
    }

    private BooleanExpression isActiveArtist() {
        return (member.deleted.isFalse()).and(artist.deleted.isFalse());
    }

    private BooleanExpression isActiveLive() {
        return live.endedAt.isNull();
    }

    private List<OrderSpecifier> getAllOrderSpecifiers(Pageable page) {

        List<OrderSpecifier> orders =
                page.getSort().stream().map(this::getOrderSpecifier).toList();
        return orders;
    }

    private OrderSpecifier getOrderSpecifier(Sort.Order order) {
        Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
        OrderSpecifier orderSpecifier = switch (order.getProperty()) {
            case "title" -> new OrderSpecifier(direction, live.title);
            case "createdAt" -> new OrderSpecifier(direction, live.createdAt);
            case "endedAt" -> new OrderSpecifier(direction, live.endedAt);
            default -> throw new InvalidSortOrderException();
        };
        return orderSpecifier;
    }
}
