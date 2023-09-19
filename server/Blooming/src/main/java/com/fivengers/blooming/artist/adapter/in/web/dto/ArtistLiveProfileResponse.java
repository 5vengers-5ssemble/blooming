package com.fivengers.blooming.artist.adapter.in.web.dto;

import com.fivengers.blooming.live.domain.Artist;
import com.fivengers.blooming.member.adapter.in.web.dto.MemberLiveProfileResponse;
import lombok.Builder;

@Builder
public record ArtistLiveProfileResponse(
        Long id,
        String stageName,
        MemberLiveProfileResponse member
) {

    public static ArtistLiveProfileResponse from(Artist artist) {
        return ArtistLiveProfileResponse.builder()
                .id(artist.getId())
                .stageName(artist.getStageName())
                .member(MemberLiveProfileResponse.from(artist.getMember()))
                .build();
    }
}
