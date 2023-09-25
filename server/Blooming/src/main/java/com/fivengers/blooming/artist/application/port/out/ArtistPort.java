package com.fivengers.blooming.artist.application.port.out;

import com.fivengers.blooming.artist.domain.Artist;
import java.util.List;
import java.util.Optional;

public interface ArtistPort {

    Artist save(Artist artist);
    List<Artist> findAll();
    Optional<Artist> findById(Long artistId);
}
