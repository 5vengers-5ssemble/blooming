package com.fivengers.blooming.artist.application.port.in;

import com.fivengers.blooming.artist.domain.Artist;
import java.util.List;

public interface ArtistUseCase {

    List<Artist> searchAll();
    Artist searchById(Long artistId);

}
