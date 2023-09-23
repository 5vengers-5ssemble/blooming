package com.fivengers.blooming.form.application.port.in.dto;

import com.fivengers.blooming.form.domain.DefaultInfo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record DefaultInfoRequest(String image,
                                 String startDate,
                                 String endDate,
                                 String title) {

    public DefaultInfo toDomain() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return DefaultInfo.builder()
                .image(image)
                .startDate(LocalDateTime.parse(startDate, formatter))
                .endDate(LocalDateTime.parse(endDate, formatter))
                .title(title)
                .build();
    }
}
