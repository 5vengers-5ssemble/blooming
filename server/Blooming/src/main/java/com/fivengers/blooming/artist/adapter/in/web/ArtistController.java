package com.fivengers.blooming.artist.adapter.in.web;

import com.fivengers.blooming.artist.adapter.in.web.dto.ArtistListResponse;
import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.global.response.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistUseCase artistUseCase;

    @GetMapping
    public ApiResponse<List<ArtistListResponse>> artistList() {
        return new ApiResponse<>(HttpStatus.OK.value(), artistUseCase.searchAll().stream()
                .map(ArtistListResponse::from)
                .toList());
    }

}