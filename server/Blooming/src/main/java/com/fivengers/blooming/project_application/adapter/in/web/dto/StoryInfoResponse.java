package com.fivengers.blooming.project_application.adapter.in.web.dto;

import com.fivengers.blooming.project_application.application.port.in.dto.MoreInfoRequest;

public record StoryInfoResponse(String introduction,
                                String description,
                                String listImage,
                                String compositionImage,
                                String teaserVideoUrl,
                                Long budget) {

}
