package com.fivengers.blooming.artist.application;

import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistCreateRequest;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistModifyRequest;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistVideoUpdateRequest;
import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.artist.application.port.out.ArtistVideoPort;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artist.domain.ArtistVideo;
import com.fivengers.blooming.global.exception.artist.ArtistNotFoundException;
import com.fivengers.blooming.global.exception.artist.InvalidArtistModifyRequestException;
import com.fivengers.blooming.global.exception.artistvideo.ArtistVideoNotFoundException;
import com.fivengers.blooming.global.exception.member.MemberNotFoundException;
import com.fivengers.blooming.member.application.port.out.MemberPort;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService implements ArtistUseCase {

    private final ArtistPort artistPort;
    private final ArtistVideoPort artistVideoPort;
    private final MemberPort memberPort;

    @Override
    public Artist add(ArtistCreateRequest request) {
        Artist artist = artistPort.save(request.toDomain(
                memberPort.findById(request.memberId()).orElseThrow(MemberNotFoundException::new)));
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
    public Artist searchByMemberId(Long memberId) {
        return artistPort.findByMemberId(memberId).orElseThrow(ArtistNotFoundException::new);
    }

    @Override
    public Artist modify(ArtistModifyRequest request, Long artistId, Long memberId) {
        Artist artist = artistPort.findById(artistId).orElseThrow(ArtistNotFoundException::new);

        if (artist.isSameMember(memberId)) {
            artist.modify(request.stageName(),
                    request.agency(),
                    request.description(),
                    request.profileImageUrl(),
                    request.youtubeUrl(),
                    request.fanCafeUrl(),
                    request.snsUrl());

            updateArtistVideos(request, artist);

            return artistPort.update(artist);
        }

        throw new InvalidArtistModifyRequestException();
    }

    private void updateArtistVideos(ArtistModifyRequest request, Artist artist) {
        Map<Long, ArtistVideo> artistVideos = artistVideoPort.findByArtistId(artist.getId())
                .stream()
                .collect(Collectors.toMap(ArtistVideo::getId, video -> video));

        request.artistVideo().forEach(video -> updateArtistVideo(video, artist, artistVideos));
        artistVideos.keySet().forEach(artistVideoPort::deleteById);
    }

    private void updateArtistVideo(ArtistVideoUpdateRequest request, Artist artist,
            Map<Long, ArtistVideo> artistVideos) {
        if (request.newVideo()) {
            artistVideoPort.save(request.toDomain(artist));
            return;
        }

        if (artistVideos.containsKey(request.id())) {
            ArtistVideo artistVideo = artistVideos.remove(request.id());
            artistVideo.modify(request.videoUrl());
            artistVideoPort.update(artistVideo);
            return;
        }

        throw new ArtistVideoNotFoundException();
    }
}
