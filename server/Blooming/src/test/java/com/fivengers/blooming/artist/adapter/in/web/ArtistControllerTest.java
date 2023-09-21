package com.fivengers.blooming.artist.adapter.in.web;

import static com.fivengers.blooming.support.ApiDocumentUtils.getDocumentRequest;
import static com.fivengers.blooming.support.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.support.RestDocsTest;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(ArtistController.class)
class ArtistControllerTest extends RestDocsTest {

    @MockBean ArtistUseCase artistUseCase;

    @Test
    @DisplayName("아티스트 목록을 조회한다.")
    void artistList() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Artist artist1 = Artist.builder()
                .id(1L)
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .createdAt(now)
                .modifiedAt(now)
                .build();
        Artist artist2 = Artist.builder()
                .id(2L)
                .stageName("박효신")
                .agency("허비그하로")
                .description("박효신입니다.")
                .createdAt(now)
                .modifiedAt(now)
                .build();
        given(artistUseCase.searchAll()).willReturn(List.of(artist1, artist2));

        ResultActions perform = mockMvc.perform(get("/api/v1/artists")
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results[0].id").value(artist1.getId()))
                .andExpect(jsonPath("$.results[1].id").value(artist2.getId()));

        perform.andDo(print())
                .andDo(document("artist-list",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }

    @Test
    @DisplayName("아티스트 상세를 조회한다.")
    void artistDetails() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Artist artist = Artist.builder()
                .id(1L)
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .createdAt(now)
                .modifiedAt(now)
                .build();

        given(artistUseCase.searchById(any(Long.class))).willReturn(artist);

        ResultActions perform = mockMvc.perform(get("/api/v1/artists/{artistId}", 1L)
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.stageName").value(artist.getStageName()));

        perform.andDo(print())
                .andDo(document("artist-details",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }
}