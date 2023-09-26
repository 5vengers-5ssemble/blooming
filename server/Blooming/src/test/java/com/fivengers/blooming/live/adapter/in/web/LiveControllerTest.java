package com.fivengers.blooming.live.adapter.in.web;

import static com.fivengers.blooming.support.ApiDocumentUtils.getDocumentRequest;
import static com.fivengers.blooming.support.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.live.adapter.in.web.dto.ConnectionTokenDetailRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.LiveCreateRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.SessionDetailRequest;
import com.fivengers.blooming.live.application.port.in.LiveArtistUseCase;
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
    @MockBean
    LiveArtistUseCase liveArtistUseCase;

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
                        getDocumentResponse(),
                        queryParameters(
                                parameterWithName("query").description("키워드"),
                                parameterWithName("page").description("페이지"),
                                parameterWithName("size").description("페이지 크기"),
                                parameterWithName("sort").description("정렬 요소,순서"))));
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
                        getDocumentResponse(),
                        queryParameters(
                                parameterWithName("query").description("키워드"),
                                parameterWithName("page").description("페이지"),
                                parameterWithName("size").description("페이지 크기"),
                                parameterWithName("sort").description("정렬 요소,순서"))));
    }

    @Test
    @DisplayName("session을 생성한다.")
    void session을_생성한다() throws Exception {
        SessionDetailRequest request = new SessionDetailRequest("exampleSessionId");

        given(liveSessionUseCase.createSession(any(SessionDetailRequest.class))).willReturn(
                "exampleSessionId");

        ResultActions perform = mockMvc.perform(post("/api/v1/lives/sessions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.sessionId").value("exampleSessionId"));

        perform.andDo(print())
                .andDo(document("live-session-create",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }

    @Test
    @DisplayName("connection을 생성한다.")
    void connection을_생성한다() throws Exception {
        given(liveSessionUseCase.createConnection(
                any(ConnectionTokenDetailRequest.class))).willReturn(
                "wss//url");

        ResultActions perform = mockMvc.perform(
                post("/api/v1/lives/sessions/{sessionId}/connections", "sessionId"));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.token").value("wss//url"));

        perform.andDo(print())
                .andDo(document("live-connection-create",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("sessionId").description("세션 ID"))));
    }

    @Test
    @DisplayName("라이브 Id로 Session Id를 조회한다.")
    void 라이브_Id로_Session_Id를_조회한다() throws Exception {
        given(liveSessionUseCase.searchSessionId(
                any(Long.class))).willReturn(
                "sessionId");

        ResultActions perform = mockMvc.perform(
                get("/api/v1/lives/{liveId}/session-id", "1"));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.sessionId").value("sessionId"));

        perform.andDo(print())
                .andDo(document("live-session-details",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("liveId").description("라이브 ID"))));
    }

    @Test
    @DisplayName("라이브를 생성한다.")
    void 라이브를_생성한다() throws Exception {
        LiveCreateRequest request = new LiveCreateRequest("찹찹", 2L);

        given(liveArtistUseCase.createLive(any(LiveCreateRequest.class))).willReturn(lives[0]);

        ResultActions perform = mockMvc.perform(post("/api/v1/lives")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.title").value(lives[0].getTitle()));

        perform.andDo(print())
                .andDo(document("live-create",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }
}