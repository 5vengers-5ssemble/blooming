package com.fivengers.blooming.project.adapter.in.web;

import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.project.adapter.in.web.dto.AdvertisingProjectResponse;
import com.fivengers.blooming.project.application.port.in.ProjectUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/advertising-projects")
@RequiredArgsConstructor
public class AdvertisingProjectController {

    private final ProjectUseCase projectUseCase;

    @GetMapping
    public ApiResponse<List<AdvertisingProjectResponse>> advertisingProjectList() {

        return ApiResponse.ok(projectUseCase.searchAdvertisingProjects().stream()
                .map(AdvertisingProjectResponse::from)
                .toList());
    }
}
