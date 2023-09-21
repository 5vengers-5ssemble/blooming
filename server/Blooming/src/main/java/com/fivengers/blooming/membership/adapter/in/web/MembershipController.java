package com.fivengers.blooming.membership.adapter.in.web;

import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.membership.adapter.in.web.dto.MembershipDetailsResponse;
import com.fivengers.blooming.membership.adapter.in.web.dto.MembershipListResponse;
import com.fivengers.blooming.membership.application.port.in.MembershipUseCase;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipCreateRequest;
import com.fivengers.blooming.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/memberships")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipUseCase membershipUseCase;

    @GetMapping
    public ApiResponse<Page<MembershipListResponse>> membershipList(Pageable pageable) {
        Page<Membership> memberships = membershipUseCase.searchLatestSeasons(pageable);

        return ApiResponse.ok(
                new PageImpl<>(memberships.stream()
                        .map(MembershipListResponse::from)
                        .toList(), pageable, memberships.getTotalElements()));
    }

    @PostMapping
    public ApiResponse<MembershipDetailsResponse> membershipCreate(MembershipCreateRequest request) {
        return ApiResponse.ok(MembershipDetailsResponse.from(membershipUseCase.add(request)));
    }
}
