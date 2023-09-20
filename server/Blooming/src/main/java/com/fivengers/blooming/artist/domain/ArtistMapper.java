package com.fivengers.blooming.artist.domain;

import org.springframework.stereotype.Component;

@Component
public class ArtistMapper {

    public Artist toDomain(ArtistJpaEntity artistJpaEntity){
        return new Artist(artistJpaEntity.getId(), artistJpaEntity.getName());
    }

    public ArtistJpaEntity toEntity(Artist artist){
        return new ArtistJpaEntity(artist.getId(), artist.getName());
    }

}
