package com.fivengers.blooming.artistscrap.domain;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ArtistScrap {

    private Long id;
    private Member member;
    private Artist artist;

    @Builder
    public ArtistScrap(Long id, Member member, Artist artist) {
        this.id = id;
        this.member = member;
        this.artist = artist;
    }
}
