package com.fivengers.blooming.artist.adapter.in.web;

import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentRequest;
import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberRole;
import com.fivengers.blooming.project.application.port.in.ActivityUseCase;
import com.fivengers.blooming.project.application.port.in.ConcertUseCase;
import com.fivengers.blooming.project.application.port.in.InvestmentOverviewUseCase;
import com.fivengers.blooming.project.domain.Activity;
import com.fivengers.blooming.project.domain.Concert;
import com.fivengers.blooming.support.docs.RestDocsTest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(ArtistProjectController.class)
public class ArtistProjectControllerTest extends RestDocsTest {

    @MockBean
    ActivityUseCase activityUseCase;
    @MockBean
    ConcertUseCase concertUseCase;
    @MockBean
    InvestmentOverviewUseCase overviewUseCase;

    Member member;
    Artist artist;
    LocalDateTime now = LocalDateTime.now();

    @BeforeEach
    void beforeEach() {
        member = Member.builder()
                .id(1L)
                .oauthProvider(AuthProvider.KAKAO)
                .oauthAccount("12434512")
                .name("이지은")
                .nickname("아이유")
                .account("account")
                .createdAt(now)
                .modifiedAt(now)
                .role(List.of(MemberRole.ROLE_USER))
                .build();
        artist = Artist.builder()
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
                .member(member)
                .build();
    }

    @Test
    @DisplayName("해당 아티스트의 최근 진행중인 콘서트 펀딩을 조회한다.")
    void ongoingConcertTest() throws Exception {
        Concert concert = Concert.builder()
                .id(1L)
                .profileImg("/profile1.png")
                .name("아이유 콘서트")
                .introduction("많이많이 와주세용")
                .targetAmount(100_000_000L)
                .fundingAmount(30_000_000L)
                .startedAt(now)
                .endedAt(now.plusMonths(3))
                .artist(artist)
                .build();
        given(concertUseCase.searchByArtistId(anyLong())).willReturn(Optional.of(concert));

        ResultActions perform = mockMvc.perform(
                get("/api/v1/artists/{artistId}/concert/ongoing", 1L)
                        .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.isExists").value(true))
                .andExpect(jsonPath("$.results.concert.id").value(1L));

        perform.andDo(print())
                .andDo(document("artist-ongoing-concert",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("artistId").description("아티스트 ID"))));
    }

    @Test
    @DisplayName("해당 아티스트가 현재 진행 중인 콘서트 펀딩이 없을 경우 false를 반환한다.")
    void ongoingConcertFailTest() throws Exception {
        Concert concert = Concert.builder()
                .id(1L)
                .profileImg("/profile1.png")
                .name("아이유 콘서트")
                .introduction("많이많이 와주세용")
                .targetAmount(100_000_000L)
                .fundingAmount(30_000_000L)
                .startedAt(now.minusDays(1))
                .endedAt(now.minusHours(1))
                .artist(artist)
                .build();
        given(concertUseCase.searchByArtistId(anyLong())).willReturn(Optional.empty());

        ResultActions perform = mockMvc.perform(
                get("/api/v1/artists/{artistId}/concert/ongoing", 1L)
                        .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.isExists").value(false));
    }

    @Test
    @DisplayName("해당 아티스트의 최근 진행중인 활동 펀딩을 조회한다.")
    void ongoingActivityTest() throws Exception {
        Activity activity = Activity.builder()
                .id(1L)
                .profileImg("/profile1.png")
                .name("아이유 콘서트")
                .introduction("많이많이 와주세용")
                .targetAmount(100_000_000L)
                .fundingAmount(30_000_000L)
                .startedAt(now)
                .endedAt(now.plusMonths(3))
                .artist(artist)
                .build();
        given(activityUseCase.searchByArtistId(anyLong())).willReturn(Optional.of(activity));

        ResultActions perform = mockMvc.perform(
                get("/api/v1/artists/{artistId}/activity/ongoing", 1L)
                        .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.isExists").value(true))
                .andExpect(jsonPath("$.results.activity.id").value(1L));

        perform.andDo(print())
                .andDo(document("artist-ongoing-activity",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("artistId").description("아티스트 ID"))));
    }
}
