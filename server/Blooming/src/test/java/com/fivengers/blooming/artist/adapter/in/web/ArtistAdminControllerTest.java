package com.fivengers.blooming.artist.adapter.in.web;

import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentRequest;
import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.artist.application.port.in.ArtistVideoUseCase;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistCreateRequest;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistVideoCreateRequest;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artist.domain.ArtistVideo;
import com.fivengers.blooming.support.docs.RestDocsTest;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(ArtistAdminController.class)
class ArtistAdminControllerTest extends RestDocsTest {

    @MockBean ArtistUseCase artistUseCase;
    @MockBean ArtistVideoUseCase artistVideoUseCase;

    @Test
    @DisplayName("관리자가 아티스트 신을 승인한다.")
    void artistCreate() throws Exception {
        ArtistCreateRequest request = new ArtistCreateRequest("아이유",
                "EDAM 엔터테인먼트",
                "아이유입니다.",
                "https://image.com",
                "https://youtube.com/iu",
                "https://cafe.daum.net/iu",
                "https://instagram.com/iu",
                new ArtistVideoCreateRequest(List.of("https://youtube.com/iu")));
        LocalDateTime now = LocalDateTime.now();
        Artist artist = Artist.builder()
                .id(1L)
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .profileImageUrl("https://image.com")
                .youtubeUrl("https://youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/iu")
                .snsUrl("https://instagram.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .build();
        ArtistVideo artistVideo1 = ArtistVideo.builder()
                .id(1L)
                .videoUrl("https://youtube.com/iu1")
                .artist(artist)
                .build();
        ArtistVideo artistVideo2 = ArtistVideo.builder()
                .id(2L)
                .videoUrl("https://youtube.com/iu2")
                .artist(artist)
                .build();
        ArtistVideo artistVideo3 = ArtistVideo.builder()
                .id(3L)
                .videoUrl("https://youtube.com/iu3")
                .artist(artist)
                .build();

        given(artistUseCase.add(any(ArtistCreateRequest.class), any(Long.class)))
                .willReturn(artist);
        given(artistVideoUseCase.searchByArtistId(any(Long.class)))
                .willReturn(List.of(artistVideo1, artistVideo2, artistVideo3));

        ResultActions perform = mockMvc.perform(post("/api/v1/admin/artists")
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("memberId", "1")
                .content(toJson(request)));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.stageName").value(artist.getStageName()));

        perform.andDo(print())
                .andDo(document("artist-create",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        queryParameters(
                                parameterWithName("memberId").description("멤버 ID"))));
    }
}