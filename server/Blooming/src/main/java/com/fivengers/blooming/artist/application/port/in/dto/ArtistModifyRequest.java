package com.fivengers.blooming.artist.application.port.in.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.validation.annotation.Validated;

public record ArtistModifyRequest(@NotBlank String stageName,
                                  @NotBlank String agency,
                                  @NotNull String description,
                                  @NotNull String profileImageUrl,
                                  @NotNull String youtubeUrl,
                                  @NotNull String fanCafeUrl,
                                  @NotNull String snsUrl,
                                  @Validated List<ArtistVideoUpdateRequest> artistVideo) {

}
