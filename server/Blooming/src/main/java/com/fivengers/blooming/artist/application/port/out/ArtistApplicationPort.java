package com.fivengers.blooming.artist.application.port.out;

import com.fivengers.blooming.artist.domain.ArtistApplication;
import com.fivengers.blooming.artist.domain.ArtistApplicationState;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArtistApplicationPort {

    Page<ArtistApplication> findByArtistApplicationState(Pageable pageable, ArtistApplicationState state);
    ArtistApplication save(ArtistApplication artistApplication);
    Optional<ArtistApplication> findById(Long applicationId);
    ArtistApplication update(ArtistApplication artistApplication);
}