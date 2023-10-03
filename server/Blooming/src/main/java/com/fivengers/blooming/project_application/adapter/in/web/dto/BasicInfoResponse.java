package com.fivengers.blooming.project_application.adapter.in.web.dto;

import java.time.LocalDateTime;

public record BasicInfoResponse(String thumbnail,
                                LocalDateTime startDate,
                                LocalDateTime endDate,
                                String title) {

}
