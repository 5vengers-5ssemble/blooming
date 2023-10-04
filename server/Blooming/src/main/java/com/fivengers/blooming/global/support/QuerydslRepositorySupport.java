package com.fivengers.blooming.global.support;

import com.mysema.commons.lang.Assert;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
public abstract class QuerydslRepositorySupport {

    private final Class<?> entityClass;
    private Querydsl querydsl;
    private EntityManager entityManager;
    private JPAQueryFactory jpaQueryFactory;

    public QuerydslRepositorySupport(Class<?> entityClass) {
        Assert.notNull(entityClass, "엔티티 클래스는 NULL일 수 없습니다.");
        this.entityClass = entityClass;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        Assert.notNull(entityManager, "EntityManager는 NULL이 될 수 없습니다.");
        this.entityManager = entityManager;

        EntityPath path = SimpleEntityPathResolver.INSTANCE
                .createPath(JpaEntityInformationSupport
                        .getEntityInformation(entityClass, entityManager).getJavaType());
        this.querydsl = new Querydsl(entityManager, new PathBuilder<>(path.getType(), path.getMetadata()));
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @PostConstruct
    public void validate() {
        Assert.notNull(entityManager, "EntityManager는 NULL이 될 수 없습니다.");
        Assert.notNull(querydsl, "Querydsl은 NULL이 될 수 없습니다.");
        Assert.notNull(jpaQueryFactory, "QueryFactory는 NULL이 될 수 없습니다.");
    }

    protected JPAQueryFactory getJpaQueryFactory() {
        return jpaQueryFactory;
    }

    protected Querydsl getQuerydsl() {
        return querydsl;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected <T> JPAQuery<T> select(Expression<T> expr) {
        return this.jpaQueryFactory.select(expr);
    }

    protected <T> JPAQuery<T> selectFrom(EntityPath<T> from) {
        return this.jpaQueryFactory.selectFrom(from);
    }

    protected <T> Page<T> applyPagination(Pageable pageable,
            Function<JPAQueryFactory, JPAQuery<T>> contentQuery,
            Function<JPAQueryFactory, JPAQuery<Long>> countQuery) {
        JPAQuery<T> jpaContentQuery = contentQuery.apply(jpaQueryFactory);

        List<T> content = this.querydsl.applyPagination(pageable, jpaContentQuery).fetch();
        JPAQuery<Long> countResult = countQuery.apply(this.jpaQueryFactory);

        return PageableExecutionUtils.getPage(content, pageable, countResult::fetchOne);
    }
}
