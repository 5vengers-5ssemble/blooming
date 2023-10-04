package com.fivengers.blooming.project_application.application.port.in.dto;

import jakarta.validation.constraints.NotBlank;

public record MoreInfoRequest(@NotBlank String description,
                              @NotBlank String listImage,
                              @NotBlank String compositionImage) {
}
