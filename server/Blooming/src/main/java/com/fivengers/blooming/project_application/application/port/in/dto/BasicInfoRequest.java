package com.fivengers.blooming.project_application.application.port.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fivengers.blooming.project_application.domain.BasicInfo;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record BasicInfoRequest(@NotBlank String thumbnail,
                               @NotNull
                               @JsonDeserialize(using = LocalDateTimeDeserializer.class)    // nested DTO에서 @JsonFormat을 사용할 때 반드시 필요
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                               LocalDateTime startDate,
                               @NotNull
                               @JsonDeserialize(using = LocalDateTimeDeserializer.class)
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                               LocalDateTime endDate,
                               @NotBlank @Size(max = 30) String title) {

    public BasicInfo toDomain() {
        return BasicInfo.builder()
                .title(title)
                .thumbnail(thumbnail)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
