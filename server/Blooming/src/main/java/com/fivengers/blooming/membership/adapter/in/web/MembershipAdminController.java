package com.fivengers.blooming.membership.adapter.in.web;

import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.membership.adapter.in.web.dto.MembershipDetailsResponse;
import com.fivengers.blooming.membership.application.port.in.MembershipUseCase;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/memberships")
@RequiredArgsConstructor
public class MembershipAdminController {

    private final MembershipUseCase membershipUseCase;

    @PostMapping
    public ApiResponse<MembershipDetailsResponse> membershipCreate(
            @RequestBody @Validated MembershipCreateRequest request) {
        return ApiResponse.ok(MembershipDetailsResponse.from(membershipUseCase.add(request)));
    }

}
