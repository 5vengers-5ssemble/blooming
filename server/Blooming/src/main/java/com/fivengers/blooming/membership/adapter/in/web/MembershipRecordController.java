package com.fivengers.blooming.membership.adapter.in.web;

import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.membership.adapter.in.web.dto.MembershipRecordResponse;
import com.fivengers.blooming.membership.application.port.in.MembershipRecordUseCase;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/memberships/{membershipId}/nft-records")
@RequiredArgsConstructor
public class MembershipRecordController {

    private final MembershipRecordUseCase membershipRecordUseCase;

    @GetMapping
    public ApiResponse<List<MembershipRecordResponse>> membershipRecordList(
            @PathVariable @Min(1) Long membershipId) {
        return ApiResponse.ok(membershipRecordUseCase.findOnLatestFourWeek(membershipId).stream()
                .map(MembershipRecordResponse::from)
                .toList());
    }
}
