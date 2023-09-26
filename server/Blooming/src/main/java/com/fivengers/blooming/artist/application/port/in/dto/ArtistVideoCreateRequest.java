package com.fivengers.blooming.artist.application.port.in.dto;

import jakarta.validation.constraints.Size;
import java.util.List;

public record ArtistVideoCreateRequest(@Size(max = 5) List<String> videoUrl) {

}
