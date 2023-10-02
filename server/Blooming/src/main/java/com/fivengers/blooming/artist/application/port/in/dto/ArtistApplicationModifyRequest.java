package com.fivengers.blooming.artist.application.port.in.dto;

import com.fivengers.blooming.artist.domain.ArtistApplicationState;
import jakarta.validation.constraints.NotNull;

public record ArtistApplicationModifyRequest(@NotNull ArtistApplicationState applicationState) {

}
