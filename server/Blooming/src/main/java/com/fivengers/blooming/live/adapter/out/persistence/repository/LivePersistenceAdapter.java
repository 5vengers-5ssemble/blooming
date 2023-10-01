package com.fivengers.blooming.live.adapter.out.persistence.repository;

import com.fivengers.blooming.live.adapter.out.persistence.entity.LiveJpaEntity;
import com.fivengers.blooming.live.adapter.out.persistence.mapper.LiveMapper;
import com.fivengers.blooming.live.application.port.out.LivePort;
import com.fivengers.blooming.live.domain.Live;
import com.fivengers.blooming.live.domain.SessionId;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LivePersistenceAdapter implements LivePort {

    private final String REDIS_LIVE_VIEWER_COUNT_KEY = "liveViewerCount";
    private final String REDIS_LIVE_STREAMER_KEY = "liveStreamer";

    private final LiveMapper liveMapper;
    private final LiveQueryRepository liveQueryRepository;
    private final LiveSpringDataRepository liveSpringDataRepository;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public Page<Live> findByKeyword(String keyword, Pageable pageable) {
        Page<LiveJpaEntity> liveJpaEntities = liveQueryRepository.findActiveLiveByTitleKeyword(
                keyword, pageable);
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
    public Optional<Long> findActiveLiveIdByArtist(Long artistId) {
        return Optional.ofNullable(liveQueryRepository.findActiveLiveIdByArtist(artistId));
    }

    @Override
    public boolean isNonExistentLive(Long liveId) {
        return liveSpringDataRepository.findLiveJpaEntityByIdAndEndedAtIsNull(liveId).isEmpty();
    }

    @Override
    public Live save(Live live) {
        LiveJpaEntity createdLiveJpaEntity = liveSpringDataRepository.save(
                liveMapper.toJpaEntity(live));
        return liveMapper.toDomain(createdLiveJpaEntity);
    }

    @Override
    public void saveActiveLiveInfo(String sessionId, String artistStageName) {
        redisTemplate.execute(new SessionCallback<>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                // Redis에 라이브 생성자 정보 등록
                operations.opsForHash().put(REDIS_LIVE_STREAMER_KEY, sessionId, artistStageName);

                // Redis에 시청자수 Set 추가 -> 스트리머가 들어갔을 때 0이 되도록 초기값 -1로 설정
                operations.opsForZSet().add(REDIS_LIVE_VIEWER_COUNT_KEY, sessionId, -1.0);

                return operations.exec();
            }
        });


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

    @Override
    public List<Live> findTopLivesByNumberOfViewers(int numberOfLives) {
        Set<TypedTuple<String>> topActiveLiveTuples = redisTemplate.opsForZSet()
                .reverseRangeByScoreWithScores(
                        REDIS_LIVE_VIEWER_COUNT_KEY,
                        Double.MIN_VALUE, Double.MAX_VALUE,
                        0, numberOfLives);
        Assert.notNull(topActiveLiveTuples, "시청자 수 정보가 null 일 수 없습니다.");

        Map<String, Integer> topActiveLivesViewerInfo = convertTupleToMap(topActiveLiveTuples);
        List<Live> topLives = new ArrayList<>(liveSpringDataRepository.findLiveJpaEntityByIdIsIn(
                topActiveLivesViewerInfo.keySet().stream()
                        .map(SessionId::new)
                        .map(SessionId::getLiveId)
                        .collect(Collectors.toSet())
        ).stream().map(liveMapper::toDomain).toList());
        topLives.forEach(live -> live.setNumberOfViewers(topActiveLivesViewerInfo.get(live.getSessionId())));
        topLives.sort((live1, live2) -> live2.getNumberOfViewers() - live1.getNumberOfViewers());
        return Collections.unmodifiableList(topLives);
    }

    private Map<String, Integer> convertTupleToMap(Set<TypedTuple<String>> typedTuples) {
        return typedTuples.stream().collect(Collectors.toMap(
                TypedTuple::getValue,
                tuple -> tuple.getScore().intValue()
        ));
    }

    @Override
    public void updateParticipantCount(String sessionId, int difference) {
        redisTemplate.opsForZSet().incrementScore(REDIS_LIVE_VIEWER_COUNT_KEY, sessionId, difference);
    }
}
