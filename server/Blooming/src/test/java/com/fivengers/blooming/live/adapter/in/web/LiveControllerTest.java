package com.fivengers.blooming.live.adapter.in.web;

import static com.fivengers.blooming.support.ApiDocumentUtils.getDocumentRequest;
import static com.fivengers.blooming.support.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.live.application.port.in.LiveSearchUseCase;
import com.fivengers.blooming.live.application.port.in.LiveSessionUseCase;
import com.fivengers.blooming.live.domain.Live;
import com.fivengers.blooming.support.RestDocsTest;
import java.time.LocalDateTime;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(LiveController.class)
class LiveControllerTest extends RestDocsTest {

    @MockBean
    LiveSearchUseCase liveSearchUseCase;
    @MockBean
    LiveSessionUseCase liveSessionUseCase;

    Artist[] artists;
    Live[] lives;
    Pageable pageable;

    @BeforeEach
    void beforeEach() {
        LocalDateTime now = LocalDateTime.now();
        pageable = PageRequest.of(0, 10, Direction.ASC, "createdAt");
        artists = new Artist[2];
        lives = new Live[2];

        for (int i = 0; i < 2; i++) {
            artists[i] = Artist.builder()
                    .id((long) i)
                    .stageName("가수" + i)
                    .agency("EDAM 엔터테인먼트")
                    .description("나는 가수" + i + "이다.")
                    .createdAt(now)
                    .modifiedAt(now)
                    .build();

            lives[i] = Live.builder()
                    .id((long) i)
                    .title("라이브" + i)
                    .artist(artists[i])
                    .createdAt(now)
                    .modifiedAt(now)
                    .build();
        }
    }


    @Test
    @DisplayName("라이브 제목 키워드로 스트리밍 중인 라이브를 검색한다.")
    void 라이브_제목_키워드로_스트리밍_중인_라이브를_검색한다() throws Exception {

        given(liveSearchUseCase.searchByKeyword(anyString(), any(Pageable.class)))
                .willReturn(new PageImpl<>(
                        Arrays.asList(lives),
                        pageable,
                        2
                ));

        ResultActions perform = mockMvc.perform(get("/api/v1/lives/search/keyword")
                .queryParam("query", "keyword")
                .queryParam("page", String.valueOf(pageable.getPageNumber()))
                .queryParam("size", String.valueOf(pageable.getPageSize()))
                .queryParam("sort", "title,desc")
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.content[0].title").value(lives[0].getTitle()));

        perform.andDo(print())
                .andDo(document("live-list-by-keyword",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }

    @Test
    @DisplayName("아티스트로 스트리밍 중인 라이브를 검색한다.")
    void 아티스트로_스트리밍_중인_라이브를_검색한다() throws Exception {

        given(liveSearchUseCase.searchByArtist(anyString(), any(Pageable.class)))
                .willReturn(new PageImpl<>(
                        Arrays.asList(lives),
                        pageable,
                        2
                ));

        ResultActions perform = mockMvc.perform(get("/api/v1/lives/search/artist")
                .queryParam("query", "keyword")
                .queryParam("page", String.valueOf(pageable.getPageNumber()))
                .queryParam("size", String.valueOf(pageable.getPageSize()))
                .queryParam("sort", "title,desc")
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.content[0].title").value(lives[0].getTitle()));

        perform.andDo(print())
                .andDo(document("live-list-by-artist",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }
}