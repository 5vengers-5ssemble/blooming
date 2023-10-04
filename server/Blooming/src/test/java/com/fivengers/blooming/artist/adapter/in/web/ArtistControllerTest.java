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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fivengers.blooming.artist.application.port.in.ArtistUseCase;
import com.fivengers.blooming.artist.application.port.in.ArtistVideoUseCase;
import com.fivengers.blooming.artist.application.port.in.dto.ArtistModifyRequest;
import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.artist.domain.ArtistVideo;
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

@WebMvcTest(ArtistController.class)
class ArtistControllerTest extends RestDocsTest {

    @MockBean
    ArtistUseCase artistUseCase;
    @MockBean
    ArtistVideoUseCase artistVideoUseCase;

    @Test
    @DisplayName("아티스트 목록을 조회한다.")
    void artistList() throws Exception {
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
        Artist artist1 = Artist.builder()
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
        Artist artist2 = Artist.builder()
                .id(2L)
                .stageName("박효신")
                .agency("허비그하로")
                .description("박효신입니다.")
                .profileImageUrl("https://image.com")
                .youtubeUrl("https://youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/iu")
                .snsUrl("https://instagram.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .member(member)
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

        given(artistUseCase.searchById(any(Long.class))).willReturn(artist);
        given(artistVideoUseCase.searchByArtistId(any(Long.class)))
                .willReturn(List.of(artistVideo1, artistVideo2, artistVideo3));

        ResultActions perform = mockMvc.perform(get("/api/v1/artists/{artistId}", 1L)
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.stageName").value(artist.getStageName()));

        perform.andDo(print())
                .andDo(document("artist-details",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("artistId").description("아티스트 ID"))));
    }

    @Test
    @DisplayName("아티스트 정보를 수정한다.")
    void artistModify() throws Exception {
        ArtistModifyRequest request = new ArtistModifyRequest(
                "아이유",
                "EDAM 엔터테인먼트",
                "아이유입니다.",
                "https://image.com",
                "https://youtube.com/iu",
                "https://cafe.daum.net/iu",
                "https://instagram.com/iu");
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

        given(artistUseCase.modify(any(ArtistModifyRequest.class), any(Long.class), any(Long.class)))
                .willReturn(artist);
        given(artistVideoUseCase.searchByArtistId(any(Long.class)))
                .willReturn(List.of(artistVideo1, artistVideo2, artistVideo3));


        ResultActions perform = mockMvc.perform(put("/api/v1/artists/{artistId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.stageName").value(artist.getStageName()));

        perform.andDo(print())
                .andDo(document("artist-modify",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("artistId").description("아티스트 ID"))));
    }
}