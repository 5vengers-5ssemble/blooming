package com.fivengers.blooming.artist.application;

import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.artist.domain.Artist;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService implements ArtistUseCase {

    private final ArtistPort artistPort;

    @Override
    public List<Artist> searchAll() {
        return artistPort.findAll();
    }
}
