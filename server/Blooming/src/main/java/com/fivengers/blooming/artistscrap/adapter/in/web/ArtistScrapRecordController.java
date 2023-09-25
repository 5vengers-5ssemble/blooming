package com.fivengers.blooming.artistscrap.adapter.in.web;

import com.fivengers.blooming.artistscrap.adapter.in.web.dto.ArtistScrapRecordResponse;
import com.fivengers.blooming.artistscrap.application.port.in.ArtistScrapRecordUseCase;
import com.fivengers.blooming.global.response.ApiResponse;
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
@RequestMapping("/api/v1/artists/{artistId}/scrap-records")
@RequiredArgsConstructor
public class ArtistScrapRecordController {

    private final ArtistScrapRecordUseCase artistScrapRecordUseCase;

    @GetMapping
    public ApiResponse<List<ArtistScrapRecordResponse>> artistScrapRecordList(@PathVariable @Min(1) Long artistId) {
        return ApiResponse.ok(artistScrapRecordUseCase.findOnLatestFourWeek(artistId).stream()
                .map(ArtistScrapRecordResponse::from)
                .toList());
    }
}
