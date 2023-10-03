package com.fivengers.blooming.artist.adapter.in.web.dto;

public record ArtistConcertResponse(Boolean isExists,
                                    ArtistProjectResponse concert) {

    public static ArtistConcertResponse empty() {

        return new ArtistConcertResponse(false, ArtistProjectResponse.empty());
    }

    public static ArtistConcertResponse from(ArtistProjectResponse concert) {

        return new ArtistConcertResponse(true, concert);
    }
}
