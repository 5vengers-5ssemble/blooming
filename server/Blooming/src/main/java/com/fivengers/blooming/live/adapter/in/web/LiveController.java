package com.fivengers.blooming.live.adapter.in.web;

import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.live.adapter.in.web.dto.LiveListResponse;
import com.fivengers.blooming.live.application.port.in.LiveSearchUseCase;
import com.fivengers.blooming.live.domain.Live;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/lives")
@RequiredArgsConstructor
public class LiveController {

    private final LiveSearchUseCase liveSearchUseCase;

    @GetMapping("/search/keyword")
    public ApiResponse<Page<LiveListResponse>> LiveListByKeyword(
            @RequestParam @NotBlank String query, Pageable pageable) {
        Page<Live> lives = liveSearchUseCase.searchByKeyword(query, pageable);

        return ApiResponse.ok(
                new PageImpl<>(
                        lives.stream().map(LiveListResponse::from).toList(),
                        pageable,
                        lives.getTotalElements()
                )
        );
    }

    @GetMapping("/search/artist")
    public ApiResponse<Page<LiveListResponse>> liveListByArtist(
            @RequestParam @NotBlank String query, Pageable pageable) {
        Page<Live> lives = liveSearchUseCase.searchByArtist(query, pageable);
        return ApiResponse.ok(
                new PageImpl<>(
                        lives.stream().map(LiveListResponse::from).toList(),
                        pageable,
                        lives.getTotalElements()
                )
        );
    }
}
