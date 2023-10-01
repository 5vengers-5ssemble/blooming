package com.fivengers.blooming.project_application.adapter.in.web;


import com.fivengers.blooming.config.security.oauth2.LoginUser;
import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.project_application.application.port.in.ProjectApplicationUseCase;
import com.fivengers.blooming.project_application.application.port.in.dto.ProjectApplicationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/project-applications")
public class ProjectApplicationController {

    private final ProjectApplicationUseCase fundAddFormUseCase;

    @PostMapping
    public ApiResponse<?> projectApplicationAdd(
            @Valid @RequestBody ProjectApplicationRequest request,
            @AuthenticationPrincipal LoginUser member) {

        fundAddFormUseCase.addProjectApplication(request.toDomain(member.getMember()));
        return ApiResponse.noContent().build();
    }

}
