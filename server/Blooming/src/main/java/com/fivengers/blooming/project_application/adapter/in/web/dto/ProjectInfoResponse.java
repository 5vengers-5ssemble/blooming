package com.fivengers.blooming.project_application.adapter.in.web.dto;

public record ProjectInfoResponse(String category,
                                  MakerInfoResponse makerInfoResponse,
                                  Long targetAmount) {

}
