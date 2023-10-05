package com.fivengers.blooming.membership.adapter.in.web;

import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentRequest;
import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.membership.application.port.in.MembershipApplicationUseCase;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipApplyRequest;
import com.fivengers.blooming.membership.domain.MembershipApplication;
import com.fivengers.blooming.membership.domain.MembershipApplicationState;
import com.fivengers.blooming.support.docs.RestDocsTest;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(MembershipApplicationController.class)
class MembershipApplicationControllerTest extends RestDocsTest {

    @MockBean
    MembershipApplicationUseCase membershipApplicationUseCase;

    @Test
    @DisplayName("멤버십 신청을 등록한다.")
    void createMembershipApplication() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        MembershipApplyRequest request = new MembershipApplyRequest("아이유 (IU)",
                "아이유입니다.",
                now,
                now.plusYears(1),
                now,
                now.plusMonths(1),
                "https://image.com/iu");

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
                .createdAt(now)
                .modifiedAt(now)
                .artist(artist)
                .build();

        given(membershipApplicationUseCase.add(any(MembershipApplyRequest.class), any(Long.class)))
                .willReturn(application);

        ResultActions perform = mockMvc.perform(post("/api/v1/membership-applications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.title").value(request.title()));

        perform.andDo(print())
                .andDo(document("membership-application-create",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }

    @Test
    @DisplayName("자신의 멤버십 신청 현황을 조회한다.")
    void getMembershipMyDetails() throws Exception {
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
                .createdAt(now)
                .modifiedAt(now)
                .artist(artist)
                .build();

        given(membershipApplicationUseCase.searchByMemberIdAndApplicationState(any(Long.class),
                any(MembershipApplicationState.class)))
                .willReturn(application);

        ResultActions perform = mockMvc.perform(get("/api/v1/membership-applications/me")
                .queryParam("state", "APPLY")
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.title").value(application.getTitle()));

        perform.andDo(print())
                .andDo(document("membership-application-my-details",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        queryParameters(
                                parameterWithName("state").description("신청 상태"))));
    }
}