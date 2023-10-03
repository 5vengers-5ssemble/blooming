package com.fivengers.blooming.artist.adapter.in.web.dto;

public record ArtistActivityResponse(Boolean isExists,
                                     ArtistProjectResponse activity) {

    public static ArtistActivityResponse empty() {

        return new ArtistActivityResponse(false, ArtistProjectResponse.empty());
    }

    public static ArtistActivityResponse from(ArtistProjectResponse activity) {

        return new ArtistActivityResponse(true, activity);
    }
}
