package com.fivengers.blooming.artist.application.port.out;

import com.fivengers.blooming.artist.domain.Artist;
import java.util.List;

public interface ArtistPort {

    Artist save(Artist artist);
    List<Artist> findAll();
    Artist findById(Long artistId);
}
