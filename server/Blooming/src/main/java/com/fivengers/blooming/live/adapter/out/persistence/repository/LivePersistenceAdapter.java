package com.fivengers.blooming.live.adapter.out.persistence.repository;

import com.fivengers.blooming.global.support.QuerydslRepositorySupport;
import com.fivengers.blooming.live.adapter.out.persistence.entity.LiveJpaEntity;
import com.fivengers.blooming.live.adapter.out.persistence.mapper.LiveMapper;
import com.fivengers.blooming.live.application.port.out.LivePort;
import com.fivengers.blooming.live.domain.Live;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LivePersistenceAdapter implements LivePort {

    private final LiveMapper liveMapper;
    private final LiveQueryRepository liveQueryRepository;
    private final LiveSpringDataRepository liveSpringDataRepository;

    @Override
    public Page<Live> findByKeyword(String keyword, Pageable pageable) {
        Page<LiveJpaEntity> liveJpaEntities = liveQueryRepository.findActiveLiveByTitleKeyword(keyword, pageable);
        return new PageImpl<>(
                liveJpaEntities.stream().map(liveMapper::toDomain).toList(),
                pageable,
                liveJpaEntities.getTotalElements()
        );
    }

    @Override
    public Page<Live> findByArtistStageName(String keyword, Pageable pageable) {
        Page<LiveJpaEntity> liveJpaEntities = liveQueryRepository.findActiveLiveByArtist(keyword,
                pageable);
        return new PageImpl<>(
                liveJpaEntities.stream().map(liveMapper::toDomain).toList(),
                pageable,
                liveJpaEntities.getTotalElements()
        );
    }

    @Override
    public boolean isActiveLive(Long liveId) {
        return liveSpringDataRepository.findLiveJpaEntityByIdAndEndedAtIsNull(liveId).isPresent();
    }

    @Override
    public boolean isNonExistentLive(Long liveId) {
        return liveSpringDataRepository.findLiveJpaEntityByIdAndEndedAtIsNull(liveId).isEmpty();
    }

    @Override
    public Live save(Live live) {
        LiveJpaEntity createdLiveJpaEntity = liveSpringDataRepository.save(liveMapper.toJpaEntity(live));
        return liveMapper.toDomain(createdLiveJpaEntity);
    }

    @Override
    public int findLiveCountByWeek(Long artistId, LocalDate endOfWeek) {
        return liveSpringDataRepository.countLiveJpaEntitiesByArtistJpaEntity_IdAndCreatedAtBetween(
                artistId,
                LocalDateTime.of(endOfWeek.minusDays(6), LocalTime.of(0, 0)),
                LocalDateTime.of(endOfWeek, LocalTime.of(23, 59))
        );
    }

    @Override
    public Page<Live> findActiveLive(Pageable pageable) {
        Page<LiveJpaEntity> liveJpaEntities = liveQueryRepository.findActiveLive(pageable);
        return new PageImpl<>(
                liveJpaEntities.stream().map(liveMapper::toDomain).toList(),
                pageable,
                liveJpaEntities.getTotalElements()
        );
    }
}
