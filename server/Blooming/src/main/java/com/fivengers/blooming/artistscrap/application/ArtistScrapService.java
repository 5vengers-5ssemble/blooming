package com.fivengers.blooming.artistscrap.application;

import com.fivengers.blooming.artist.application.port.out.ArtistPort;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artistscrap.application.port.in.ArtistScrapUseCase;
import com.fivengers.blooming.artistscrap.application.port.in.dto.ArtistScrapRequest;
import com.fivengers.blooming.artistscrap.application.port.out.ArtistScrapPort;
import com.fivengers.blooming.artistscrap.domain.ArtistScrap;
import com.fivengers.blooming.artistscrap.domain.ArtistScrapRecord;
import com.fivengers.blooming.global.exception.artist.ArtistNotFoundException;
import com.fivengers.blooming.member.application.port.out.MemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistScrapService implements ArtistScrapUseCase {

    private final ArtistScrapRecordService artistScrapRecordService;
    private final ArtistScrapPort artistScrapPort;
    private final MemberPort memberPort;
    private final ArtistPort artistPort;

    @Override
    public void scrap(ArtistScrapRequest request, Long artistId) {
        Artist artist = artistPort.findById(artistId).orElseThrow(ArtistNotFoundException::new);
        artistScrapPort.saveScrap(ArtistScrap.builder()
                .member(memberPort.findById(request.memberId()))
                .artist(artist)
                .build());
        artistScrapRecordService.recordIfOnWeek(artist, ArtistScrapRecord::upCount);
    }

    @Override
    public void unScrap(ArtistScrapRequest request, Long artistId) {
        artistScrapPort.deleteScrap(request.memberId(), artistId);
        artistScrapRecordService.recordIfOnWeek(
                        artistPort.findById(artistId).orElseThrow(ArtistNotFoundException::new),
                        ArtistScrapRecord::downCount);
    }
}
