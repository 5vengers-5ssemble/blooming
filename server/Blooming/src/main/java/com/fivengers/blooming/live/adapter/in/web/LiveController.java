package com.fivengers.blooming.live.adapter.in.web;

import com.fivengers.blooming.global.response.ApiResponse;
import com.fivengers.blooming.live.adapter.in.web.dto.BestLiveListResponse;
import com.fivengers.blooming.live.adapter.in.web.dto.ConnectionTokenDetailRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.ConnectionTokenDetailResponse;
import com.fivengers.blooming.live.adapter.in.web.dto.FrequencyInfoResponse;
import com.fivengers.blooming.live.adapter.in.web.dto.LiveCheckActiveResponse;
import com.fivengers.blooming.live.adapter.in.web.dto.LiveCreateRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.LiveDetailsResponse;
import com.fivengers.blooming.live.adapter.in.web.dto.LiveFrequencyDetailsRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.LiveFrequencyDetailsResponse;
import com.fivengers.blooming.live.adapter.in.web.dto.LiveListResponse;
import com.fivengers.blooming.live.adapter.in.web.dto.OpenviduWebHookResponse;
import com.fivengers.blooming.live.adapter.in.web.dto.OpenviduWebhookRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.SessionDetailRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.SessionDetailResponse;
import com.fivengers.blooming.live.application.port.in.LiveArtistUseCase;
import com.fivengers.blooming.live.application.port.in.LiveSearchUseCase;
import com.fivengers.blooming.live.application.port.in.LiveSessionUseCase;
import com.fivengers.blooming.live.domain.Live;
import com.fivengers.blooming.live.domain.LiveFrequency;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/lives")
@RequiredArgsConstructor
public class LiveController {

    private final LiveSearchUseCase liveSearchUseCase;
    private final LiveSessionUseCase liveSessionUseCase;
    private final LiveArtistUseCase liveArtistUseCase;

    @PostMapping()
    public ApiResponse<LiveDetailsResponse> liveCreate(
            @RequestBody @Validated LiveCreateRequest liveCreateRequest
    ) {
        Live createdLive = liveArtistUseCase.createLive(liveCreateRequest);
        return ApiResponse.ok(LiveDetailsResponse.from(createdLive));
    }

    @GetMapping()
    public ApiResponse<Page<LiveListResponse>> activeLiveList(Pageable pageable) {
        Page<Live> lives = liveSearchUseCase.searchActiveLive(pageable);
        return ApiResponse.ok(
                new PageImpl<>(
                        lives.stream().map(LiveListResponse::from).toList(),
                        pageable,
                        lives.getTotalElements()));
    }


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

    @PostMapping("/sessions")
    public ApiResponse<SessionDetailResponse> sessionCreate(@RequestBody @Validated
    SessionDetailRequest sessionDetailRequest)
            throws OpenViduJavaClientException, OpenViduHttpException {
        String sessionId = liveSessionUseCase.createSession(sessionDetailRequest);
        return ApiResponse.ok(SessionDetailResponse.from(sessionId));
    }

    @PostMapping("/sessions/{sessionId}/connections")
    public ApiResponse<ConnectionTokenDetailResponse> connectionCreate(@PathVariable("sessionId")
    ConnectionTokenDetailRequest connectionTokenDetailRequest)
            throws OpenViduJavaClientException, OpenViduHttpException {
        String connectionToken = liveSessionUseCase.createConnection(connectionTokenDetailRequest);
        return ApiResponse.ok(ConnectionTokenDetailResponse.from(connectionToken));
    }

    @GetMapping("/{liveId}/session-id")
    public ApiResponse<SessionDetailResponse> sessionDetails(
            @Min(1) @PathVariable("liveId") Long liveId) {
        String sessionId = liveSessionUseCase.searchSessionId(liveId);
        return ApiResponse.ok(SessionDetailResponse.from(sessionId));
    }

    @GetMapping("/frequencies")
    public ApiResponse<LiveFrequencyDetailsResponse> liveFrequencyDetail(
            @Validated LiveFrequencyDetailsRequest liveFrequencyDetailsRequest) {
        List<LiveFrequency> liveFrequencies = liveSearchUseCase.searchLiveFrequencyByArtist(
                liveFrequencyDetailsRequest);
        List<FrequencyInfoResponse> frequencyInfoResponses = liveFrequencies.stream()
                .map(FrequencyInfoResponse::from).toList();
        return ApiResponse.ok(LiveFrequencyDetailsResponse.from(
                liveFrequencyDetailsRequest.artistId(),
                frequencyInfoResponses));
    }

    @GetMapping("/check/active")
    public ApiResponse<LiveCheckActiveResponse> liveActiveCheck(
            @NotNull @Min(1) @RequestParam Long artistId) {
        Long hasActiveLive = liveSearchUseCase.checkActiveLive(artistId);
        return ApiResponse.ok(new LiveCheckActiveResponse(artistId, hasActiveLive));
    }

    @GetMapping("/best")
    public ApiResponse<List<BestLiveListResponse>> bestLiveList(
            @NotNull @Min(1) @RequestParam(defaultValue = "3") Integer numberOfLives) {
        List<Live> lives = liveSearchUseCase.searchBestLive(numberOfLives);
        return ApiResponse.ok(
                lives.stream().map(BestLiveListResponse::from)
                        .toList());
    }

    @PostMapping("/openvidu/webhook")
    public ApiResponse<OpenviduWebHookResponse> openviduWebHook(
            @RequestBody OpenviduWebhookRequest openviduWebhookRequest
    ) {
        log.info("Openvidu webHook : {}", openviduWebhookRequest);

        return ApiResponse.ok(new OpenviduWebHookResponse("SUCCESS"));
    }
}
