package com.fivengers.blooming.member.adapter.in.web;

import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentRequest;
import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artistscrap.application.port.in.ArtistScrapUseCase;
import com.fivengers.blooming.artistscrap.domain.ArtistScrap;
import com.fivengers.blooming.member.application.port.in.MemberUseCase;
import com.fivengers.blooming.member.application.port.in.dto.MemberModifyRequest;
import com.fivengers.blooming.member.domain.AuthProvider;
import com.fivengers.blooming.member.domain.Member;
import com.fivengers.blooming.member.domain.MemberRole;
import com.fivengers.blooming.support.docs.RestDocsTest;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(MemberController.class)
class MemberControllerTest extends RestDocsTest {

    @MockBean
    ArtistScrapUseCase artistScrapUseCase;
    @MockBean
    MemberUseCase memberUseCase;
    @MockBean
    ArtistUseCase artistUseCase;

    @Test
    @DisplayName("자신이 스크랩한 아티스트 목록을 조회한다.")
    void findMyScrapArtists() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Member member = Member.builder()
                .id(1L)
                .oauthProvider(AuthProvider.KAKAO)
                .oauthAccount("12434512")
                .name("이지은")
                .nickname("아이유")
                .createdAt(now)
                .modifiedAt(now)
                .role(List.of(MemberRole.ROLE_USER))
                .build();
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
                .member(member)
                .build();
        ArtistScrap artistScrap = ArtistScrap.builder()
                .id(1L)
                .member(member)
                .artist(artist)
                .build();
        given(artistScrapUseCase.searchByMemberId(any(Long.class)))
                .willReturn(List.of(artistScrap));

        ResultActions perform = mockMvc.perform(get("/api/v1/members/me/scrap-artists"));

        perform.andExpect(status().isOk());

        perform.andDo(print())
                .andDo(document("my-scrap-artists",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }

    @Test
    @DisplayName("자신의 멤버 정보를 조회한다.")
    void myMemberDetails() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Member member = Member.builder()
                .id(1L)
                .oauthProvider(AuthProvider.KAKAO)
                .oauthAccount("12434512")
                .name("이지은")
                .nickname("아이유")
                .createdAt(now)
                .modifiedAt(now)
                .role(List.of(MemberRole.ROLE_USER))
                .build();
        given(memberUseCase.searchById(any(Long.class)))
                .willReturn(member);

        ResultActions perform = mockMvc.perform(get("/api/v1/members/me")
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk());

        perform.andDo(print())
                .andDo(document("member-my-details",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }

    @Test
    @DisplayName("멤버 정보를 수정한다.")
    void modifyMember() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        MemberModifyRequest request = new MemberModifyRequest("박효신");
        Member member = Member.builder()
                .id(1L)
                .oauthProvider(AuthProvider.KAKAO)
                .oauthAccount("12434512")
                .name("이지은")
                .nickname("박효신")
                .createdAt(now)
                .modifiedAt(now)
                .role(List.of(MemberRole.ROLE_USER))
                .build();
        given(memberUseCase.modify(any(MemberModifyRequest.class), any(Long.class),
                any(Long.class)))
                .willReturn(member);

        ResultActions perform = mockMvc.perform(put("/api/v1/members/{memberId}", 1L)
                .content(toJson(request))
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.nickname").value(request.nickname()));

        perform.andDo(print())
                .andDo(document("member-modify",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("memberId").description("멤버 ID"))));
    }
}