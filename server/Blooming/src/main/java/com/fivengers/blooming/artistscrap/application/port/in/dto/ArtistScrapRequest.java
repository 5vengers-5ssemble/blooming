package com.fivengers.blooming.artistscrap.application.port.in.dto;

import jakarta.validation.constraints.NotNull;

public record ArtistScrapRequest(@NotNull Long memberId) {

}
