package com.fivengers.blooming.membership.adapter.in.web;

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

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.membership.application.port.in.MembershipApplicationUseCase;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipApplicationModifyRequest;
import com.fivengers.blooming.membership.domain.MembershipApplication;
import com.fivengers.blooming.membership.domain.MembershipApplicationState;
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

@WebMvcTest(MembershipApplicationAdminController.class)
class MembershipApplicationAdminControllerTest extends RestDocsTest {

    @MockBean
    MembershipApplicationUseCase membershipApplicationUseCase;

    @Test
    @DisplayName("관리자가 멤버십 신청 목록을 조회한다.")
    void getMembershipApplicationList() throws Exception {
        LocalDateTime now = LocalDateTime.now();

        Artist artist = Artist.builder()
                .id(1L)
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .profileImageUrl("https://image.com/iu")
                .youtubeUrl("https://youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/iu")
                .snsUrl("https://instagram.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .build();
        MembershipApplication application = MembershipApplication.builder()
                .id(1L)
                .title("아이유 (IU)")
                .description("아이유입니다.")
                .seasonStart(now)
                .seasonEnd(now.plusYears(1))
                .purchaseStart(now)
                .purchaseEnd(now.plusMonths(1))
                .thumbnailUrl("https://image.com/iu")
                .applicationState(MembershipApplicationState.APPLY)
                .createdAt(now)
                .modifiedAt(now)
                .artist(artist)
                .build();

        given(membershipApplicationUseCase.searchAll(any(Pageable.class),
                any(MembershipApplicationState.class)))
                .willReturn(new PageImpl<>(List.of(application), PageRequest.of(0, 10), 1));

        ResultActions perform = mockMvc.perform(get("/api/v1/admin/membership-applications")
                .queryParam("state", "APPLY")
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.content[0].title").value(application.getTitle()))
                .andExpect(jsonPath("$.results.content[0].applicationState")
                        .value(application.getApplicationState().getValue()));

        perform.andDo(print())
                .andDo(document("membership-application-list",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        queryParameters(
                                parameterWithName("state").description("신청 상태"))));
    }

    @Test
    @DisplayName("관리자가 멤버십 신청 상태를 수정한다.")
    void MembershipApplicationStateModify() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        MembershipApplicationModifyRequest request =
                new MembershipApplicationModifyRequest(MembershipApplicationState.APPROVAL);
        Artist artist = Artist.builder()
                .id(1L)
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .profileImageUrl("https://image.com/iu")
                .youtubeUrl("https://youtube.com/iu")
                .fanCafeUrl("https://cafe.daum.net/iu")
                .snsUrl("https://instagram.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .build();
        MembershipApplication application = MembershipApplication.builder()
                .id(1L)
                .title("아이유 (IU)")
                .description("아이유입니다.")
                .seasonStart(now)
                .seasonEnd(now.plusYears(1))
                .purchaseStart(now)
                .purchaseEnd(now.plusMonths(1))
                .thumbnailUrl("https://image.com/iu")
                .applicationState(MembershipApplicationState.APPROVAL)
                .createdAt(now)
                .modifiedAt(now)
                .artist(artist)
                .build();

        given(membershipApplicationUseCase.modifyStateById(
                any(MembershipApplicationModifyRequest.class), any(Long.class)))
                .willReturn(application);

        ResultActions perform = mockMvc.perform(
                put("/api/v1/admin/membership-applications/{applicationId}/states", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.applicationState")
                        .value(request.applicationState().getValue()));

        perform.andDo(print())
                .andDo(document("membership-application-state-modify",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("applicationId").description("멤버십 신청 ID"))));
    }
}