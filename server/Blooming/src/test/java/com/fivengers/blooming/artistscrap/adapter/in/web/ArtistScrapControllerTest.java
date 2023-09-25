package com.fivengers.blooming.artistscrap.adapter.in.web;

import static com.fivengers.blooming.support.ApiDocumentUtils.getDocumentRequest;
import static com.fivengers.blooming.support.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fivengers.blooming.artistscrap.application.port.in.ArtistScrapUseCase;
import com.fivengers.blooming.artistscrap.application.port.in.dto.ArtistScrapRequest;
import com.fivengers.blooming.support.RestDocsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(ArtistScrapController.class)
class ArtistScrapControllerTest extends RestDocsTest {

    @MockBean ArtistScrapUseCase artistScrapUseCase;

    @Test
    @DisplayName("스크랩한다.")
    void scrapCreate() throws Exception {
        ArtistScrapRequest request = new ArtistScrapRequest(1L);
        ResultActions perform = mockMvc.perform(post("/api/v1/artists/{artistId}/scrap", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        perform.andExpect(status().isNoContent())
                .andExpect(jsonPath("$.status").value(204));

        perform.andDo(print())
                .andDo(document("artist-scrap",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }

    @Test
    @DisplayName("스크랩을 취소한다.")
    void scrapRemove() throws Exception {
        ArtistScrapRequest request = new ArtistScrapRequest(1L);
        ResultActions perform = mockMvc.perform(post("/api/v1/artists/{artistId}/unscrap", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        perform.andExpect(status().isNoContent())
                .andExpect(jsonPath("$.status").value(204));

        perform.andDo(print())
                .andDo(document("artist-unscrap",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }
}