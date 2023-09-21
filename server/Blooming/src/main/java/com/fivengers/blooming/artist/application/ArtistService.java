package com.fivengers.blooming.artist.application;

import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistCreateRequest;
import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.member.application.port.out.MemberPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService implements ArtistUseCase {

    private final ArtistPort artistPort;
    private final MemberPort memberPort;

    @Override
    public Artist add(ArtistCreateRequest request, Long memberId) {
        return artistPort.save(request.toDomain(memberPort.findById(memberId)));
    }

    @Override
    public List<Artist> searchAll() {
        return artistPort.findAll();
    }

    @Override
    public Artist searchById(Long artistId) {
        return artistPort.findById(artistId);
    }
}
