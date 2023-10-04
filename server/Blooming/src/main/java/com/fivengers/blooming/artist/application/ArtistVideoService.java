package com.fivengers.blooming.artist.application;

import com.fivengers.blooming.artist.application.port.in.ArtistVideoUseCase;
import com.fivengers.blooming.artist.application.port.out.ArtistVideoPort;
import com.fivengers.blooming.artist.domain.ArtistVideo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistVideoService implements ArtistVideoUseCase {

    private final ArtistVideoPort artistVideoPort;

    @Override
    public List<ArtistVideo> searchByArtistId(Long artistId) {
        return artistVideoPort.findByArtistId(artistId);
    }
}
