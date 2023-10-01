package com.fivengers.blooming.live.adapter.in.web;

import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentRequest;
import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.live.adapter.in.web.dto.ConnectionTokenDetailRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.LiveCreateRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.LiveFrequencyDetailsRequest;
import com.fivengers.blooming.live.adapter.in.web.dto.SessionDetailRequest;
import com.fivengers.blooming.live.application.port.in.LiveArtistUseCase;
import com.fivengers.blooming.live.application.port.in.LiveSearchUseCase;
import com.fivengers.blooming.live.application.port.in.LiveSessionUseCase;
import com.fivengers.blooming.live.domain.Live;
import com.fivengers.blooming.live.domain.LiveFrequency;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.support.docs.RestDocsTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
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
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(LiveController.class)
class LiveControllerTest extends RestDocsTest {

    @MockBean
    LiveSearchUseCase liveSearchUseCase;
    @MockBean
    LiveSessionUseCase liveSessionUseCase;
    @MockBean
    LiveArtistUseCase liveArtistUseCase;

    Member[] members;
    Artist[] artists;
    Live[] lives;
    Live[] closedLives;
    Pageable pageable;

    @BeforeEach
    void beforeEach() {
        LocalDateTime now = LocalDateTime.now();
        pageable = PageRequest.of(0, 10, Direction.ASC, "createdAt");
        members = new Member[2];
        artists = new Artist[2];
        lives = new Live[2];
        closedLives = new Live[2];
        int[] numberOfViewers = {954, 530};

        int numberOfSamples = 2;
        for (int i = 0; i < numberOfSamples; i++) {
            members[i] = Member.builder()
                    .id((long) i + 1)
                    .createdAt(now)
                    .modifiedAt(now)
                    .build();
            artists[i] = Artist.builder()
                    .id((long) i+1)
                    .stageName("가수" + i)
                    .agency("EDAM 엔터테인먼트")
                    .description("나는 가수" + i + "이다.")
                    .profileImageUrl("img/profile.png")
                    .createdAt(now)
                    .modifiedAt(now)
                    .build();

            lives[i] = Live.builder()
                    .id((long) i+1)
                    .title("라이브" + i)
                    .numberOfViewers(numberOfViewers[i])
                    .artist(artists[i])
                    .createdAt(now)
                    .modifiedAt(now)
                    .build();
            closedLives[i] = Live.builder()
                    .id((long) i + 1 + numberOfSamples)
                    .title("종료된 라이브" + i)
                    .artist(artists[i])
                    .createdAt(now.minusHours(1))
                    .modifiedAt(now)
                    .endedAt(now)
                    .build();
        }
    }

    @Test
    @DisplayName("진행중인 라이브를 페이지 단위로 조회할 수 있다.")
    public void 진행중인_라이브를_페이지_단위로_조회할_수_있다() throws Exception{
        given(liveSearchUseCase.searchActiveLive(any(Pageable.class)))
                .willReturn(new PageImpl<>(
                        Arrays.asList(lives),
                        pageable,
                        2
                ));

        ResultActions perform = mockMvc.perform(get("/api/v1/lives")
                .queryParam("page", String.valueOf(pageable.getPageNumber()))
                .queryParam("size", String.valueOf(pageable.getPageSize()))
                .queryParam("sort", "title,desc")
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.content[0].title").value(lives[0].getTitle()));

        perform.andDo(print())
                .andDo(document("live-list",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        queryParameters(
                                parameterWithName("page").description("페이지"),
                                parameterWithName("size").description("페이지 크기"),
                                parameterWithName("sort").description("정렬 요소,순서"))));

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

    @Test
    @DisplayName("라이브를 생성한 아티스트가 라이브를 종료한다.")
    void 라이브를_생성한_아티스트가_라이브를_종료한다() throws Exception {
        given(liveArtistUseCase.closeLive(any(Long.class), any(Member.class))).willReturn(
                closedLives[0]);

        ResultActions perform = mockMvc.perform(put("/api/v1/lives/{liveId}/close", "1")
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.endedAt").exists());

        perform.andDo(print())
                .andDo(document("live-close",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("liveId").description("종료할 라이브 ID"))));
    }


    @Test
    @DisplayName("최근 N주의 특정 아티스트의 라이브 빈도를 조회한다.")
    void 최근_N주의_특정_아티스트의_라이브_빈도를_조회한다() throws Exception {

        given(liveSearchUseCase.searchLiveFrequencyByArtist(any(LiveFrequencyDetailsRequest.class)))
                .willReturn(List.of(
                        new LiveFrequency(LocalDate.now(), LocalDate.now(), 1),
                        new LiveFrequency(LocalDate.now(), LocalDate.now(), 2),
                        new LiveFrequency(LocalDate.now(), LocalDate.now(), 3),
                        new LiveFrequency(LocalDate.now(), LocalDate.now(), 4)
                        ));

        ResultActions perform = mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/lives/frequencies")
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("artistId", "1")
                .queryParam("numberOfWeeks", "4"));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.artistId").value(1))
                .andExpect(jsonPath("$.results.frequencies[0].count").value(1));

        perform.andDo(print())
                .andDo(document("live-frequency",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }

    @Test
    @DisplayName("특정 아티스트의 진행중인 라이브가 있을 경우 라이브 Id로 응답한다.")
    void 특정_라이브가_진행중인지_여부를_조회할_수_있다() throws Exception {

        given(liveSearchUseCase.checkActiveLive(any(Long.class)))
                .willReturn(3L);

        ResultActions perform = mockMvc.perform(get("/api/v1/lives/check/active")
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("artistId", "1"));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.activeLiveId").value(3L));

        perform.andDo(print())
                .andDo(document("live-active-check-true",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        queryParameters(
                                parameterWithName("artistId").description("아티스트 ID")
                        )));
    }

    @Test
    @DisplayName("특정 아티스트의 진행중인 라이브가 없을 경우 라이브 Id를 -1로 응답한다.")
    void 특정_아티스트의_진행중인_라이브가_없을_경우_음수의_라이브_Id로_응답한다() throws Exception {

        given(liveSearchUseCase.checkActiveLive(any(Long.class)))
                .willReturn(-1L);

        ResultActions perform = mockMvc.perform(get("/api/v1/lives/check/active")
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("artistId", "2"));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.activeLiveId").value(-1L));

        perform.andDo(print())
                .andDo(document("live-active-check-false",
                        getDocumentResponse(),
                        queryParameters(
                                parameterWithName("artistId").description("아티스트 ID")
                        )));
    }

    @Test
    @DisplayName("많은 시청자 순으로 N개의 라이브 정보를 시청자 수와 함께 조회한다.")
    void 많은_시청자_순으로_N개의_라이브_정보를_시청자_수와_함께_조회한다() throws Exception {
        given(liveSearchUseCase.searchBestLive(any(int.class)))
                .willReturn(Arrays.asList(lives));

        ResultActions perform = mockMvc.perform(get("/api/v1/lives/best")
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("numberOfLives", "2"));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results[0].id").value(1));

        perform.andDo(print())
                .andDo(document("live-best-list",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        queryParameters(
                                parameterWithName("numberOfLives").description("조회할 라이브 수")
                        )));
    }

}