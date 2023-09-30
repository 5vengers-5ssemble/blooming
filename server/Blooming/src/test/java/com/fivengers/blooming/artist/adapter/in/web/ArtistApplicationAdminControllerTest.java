package com.fivengers.blooming.artist.adapter.in.web;

import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentRequest;
import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fivengers.blooming.artist.application.port.in.ArtistApplicationUseCase;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistApplicationStateModifyRequest;
import com.fivengers.blooming.artist.domain.ArtistApplication;
import com.fivengers.blooming.artist.domain.ArtistApplicationState;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.support.docs.RestDocsTest;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(ArtistApplicationAdminController.class)
class ArtistApplicationAdminControllerTest extends RestDocsTest {

    @MockBean
    ArtistApplicationUseCase artistApplicationUseCase;

    @Test
    @DisplayName("아티스트 신청을 신청 상태 별로 요청한다.")
    void artistApplicationsByArtistApplicationState() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        ArtistApplication response = ArtistApplication.builder()
                .id(1L)
                .stageName("아이유 (IU)")
                .description("아이유입니다.")
                .agency("EDAM 엔터테인먼트")
                .applicationState(ArtistApplicationState.APPLY)
                .profileImageUrl("https://image.com/iu")
                .youtubeUrl("https://www.youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/iu")
                .snsUrl("https://instagram.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .member(Member.builder().build())
                .build();

        given(artistApplicationUseCase.searchByArtistApplicationState(
                any(Pageable.class),
                any(ArtistApplicationState.class)))
                .willReturn(new PageImpl<>(List.of(response), PageRequest.of(0, 10), 1));

        ResultActions perform = mockMvc.perform(get("/api/v1/admin/artist-applications")
                .queryParam("state", "APPLY")
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.content[0].id").value(response.getId()))
                .andExpect(jsonPath("$.results.content[0].applicationState")
                        .value(response.getApplicationState().getValue()));

        perform.andDo(print())
                .andDo(document("artist-application-list-by-state",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        queryParameters(
                                parameterWithName("state").description("아티스트 신청 상태")
                        )));
    }

    @Test
    @DisplayName("아티스트 신청 상태를 변경한다.")
    void artistApplicationStateModify() throws Exception {
        ArtistApplicationStateModifyRequest request =
                new ArtistApplicationStateModifyRequest(ArtistApplicationState.APPROVAL);
        LocalDateTime now = LocalDateTime.now();
        ArtistApplication response = ArtistApplication.builder()
                .id(1L)
                .stageName("아이유 (IU)")
                .description("아이유입니다.")
                .agency("EDAM 엔터테인먼트")
                .applicationState(ArtistApplicationState.APPROVAL)
                .profileImageUrl("https://image.com/iu")
                .youtubeUrl("https://www.youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/iu")
                .snsUrl("https://instagram.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .member(Member.builder().build())
                .build();

        given(artistApplicationUseCase.modifyStateById(
                any(Long.class),
                any(ArtistApplicationStateModifyRequest.class)))
                .willReturn(response);

        ResultActions perform = mockMvc.perform(
                put("/api/v1/admin/artist-applications/{applicationId}/states", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.applicationState")
                        .value(response.getApplicationState().getValue()));

        perform.andDo(print())
                .andDo(document("artist-application-modify",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("applicationId").description("아티스트 신청 ID")
                        )));
    }
}