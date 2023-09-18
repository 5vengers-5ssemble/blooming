package com.fivengers.blooming.Concert.domain;

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
