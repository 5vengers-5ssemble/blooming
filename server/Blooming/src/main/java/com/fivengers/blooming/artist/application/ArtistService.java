package com.fivengers.blooming.artist.application;

import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistCreateRequest;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistModifyRequest;
import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.artist.application.port.out.ArtistVideoPort;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artist.domain.ArtistVideo;
import com.fivengers.blooming.global.exception.artist.ArtistNotFoundException;
import com.fivengers.blooming.global.exception.member.MemberNotFoundException;
import com.fivengers.blooming.member.application.port.out.MemberPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService implements ArtistUseCase {

    private final ArtistPort artistPort;
    private final ArtistVideoPort artistVideoPort;
    private final MemberPort memberPort;

    @Override
    public Artist add(ArtistCreateRequest request, Long memberId) {
        Artist artist = artistPort.save(request.toDomain(
                memberPort.findById(memberId).orElseThrow(MemberNotFoundException::new)));
        request.artistVideo().videoUrl()
                .forEach(video -> artistVideoPort.save(ArtistVideo.builder()
                        .videoUrl(video)
                        .artist(artist)
                        .build()));
        return artist;
    }

    @Override
    public List<Artist> searchAll() {
        return artistPort.findAll();
    }

    @Override
    public Artist searchById(Long artistId) {
        return artistPort.findById(artistId).orElseThrow(ArtistNotFoundException::new);
    }

    @Override
    public Artist modify(ArtistModifyRequest request) {
        return artistPort.update(request.toDomain());
    }
}
