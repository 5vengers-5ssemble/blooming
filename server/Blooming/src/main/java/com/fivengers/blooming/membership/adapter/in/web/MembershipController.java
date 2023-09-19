package com.fivengers.blooming.membership.adapter.in.web;

import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.membership.adapter.in.web.dto.MembershipListResponse;
import com.fivengers.blooming.membership.application.port.in.MembershipUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/memberships")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipUseCase membershipUseCase;

    @GetMapping
    public ApiResponse<List<MembershipListResponse>> membershipList(Pageable pageable) {
        return ApiResponse.ok(membershipUseCase.searchLatestSeasons(pageable).stream()
                .map(MembershipListResponse::from)
                .toList());
    }
}
