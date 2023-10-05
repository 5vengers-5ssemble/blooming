package com.fivengers.blooming.membership.adapter.in.web;

import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.membership.adapter.in.web.dto.MembershipApplicationDetailsResponse;
import com.fivengers.blooming.membership.adapter.in.web.dto.MembershipApplicationListResponse;
import com.fivengers.blooming.membership.application.port.in.MembershipApplicationUseCase;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipApplicationModifyRequest;
import com.fivengers.blooming.membership.domain.MembershipApplication;
import com.fivengers.blooming.membership.domain.MembershipApplicationState;
import com.fivengers.blooming.project.adapter.in.web.dto.ArtistResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/membership-applications")
@RequiredArgsConstructor
public class MembershipApplicationAdminController {

    private final MembershipApplicationUseCase membershipApplicationUseCase;

    @GetMapping
    public ApiResponse<Page<MembershipApplicationListResponse>> membershipApplicationList(
            Pageable pageable,
            @RequestParam(required = false) MembershipApplicationState state) {
        Page<MembershipApplication> membershipApplications = membershipApplicationUseCase
                .searchAll(pageable, state);

        List<MembershipApplicationListResponse> responses = membershipApplications.getContent()
                .stream()
                .map(application -> MembershipApplicationListResponse.from(application,
                        ArtistResponse.from(application.getArtist())))
                .toList();

        return ApiResponse.ok(
                new PageImpl<>(responses, pageable, membershipApplications.getTotalElements()));
    }

    @PutMapping("{applicationId}/states")
    public ApiResponse<MembershipApplicationDetailsResponse> membershipApplicationStateModify(
            @PathVariable Long applicationId,
            @RequestBody @Validated MembershipApplicationModifyRequest request) throws Exception {
        MembershipApplication membershipApplication = membershipApplicationUseCase
                .modifyStateById(request, applicationId);

        return ApiResponse.ok(MembershipApplicationDetailsResponse.from(membershipApplication,
                ArtistResponse.from(membershipApplication.getArtist())));
    }

}
