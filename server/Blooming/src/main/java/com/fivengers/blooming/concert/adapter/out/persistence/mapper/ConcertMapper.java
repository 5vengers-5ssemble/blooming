package com.fivengers.blooming.concert.adapter.out.persistence.mapper;

import com.fivengers.blooming.concert.adapter.out.persistence.entity.ConcertJpaEntity;
import com.fivengers.blooming.concert.domain.Concert;
import org.springframework.stereotype.Component;

@Component
public class ConcertMapper {

    public Concert toDomain(ConcertJpaEntity concertJpaEntity){
        return new Concert(concertJpaEntity.getId(), concertJpaEntity.getName());
    }

    public ConcertJpaEntity toEntity(Concert concert){
        return new ConcertJpaEntity(concert.getId(), concert.getName());
    }

}
