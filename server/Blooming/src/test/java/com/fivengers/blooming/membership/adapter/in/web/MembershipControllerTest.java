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
import com.fivengers.blooming.membership.application.port.in.MembershipUseCase;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipModifyRequest;
import com.fivengers.blooming.membership.domain.Membership;
import com.fivengers.blooming.membership.domain.NftSale;
import com.fivengers.blooming.support.docs.RestDocsTest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(MembershipController.class)
class MembershipControllerTest extends RestDocsTest {

    @MockBean
    MembershipUseCase membershipUseCase;

    @Test
    @DisplayName("멤버십 목록을 조회한다")
    void membershipList() throws Exception {
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
        NftSale nftSale = NftSale.builder()
                .id(1L)
                .totalNftCount(1)
                .soldNftCount(0)
                .totalNftAmount(10000L)
                .soldNftAmount(0L)
                .createdAt(now)
                .modifiedAt(now)
                .build();
        Membership membership = Membership.builder()
                .id(1L)
                .title("아이유 (IU)")
                .description("아이유입니다.")
                .season(1)
                .seasonStart(now)
                .seasonEnd(now.plusYears(1))
                .purchaseStart(now)
                .purchaseEnd(now.plusMonths(1))
                .saleCount(0)
                .thumbnailUrl("https://image.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .artist(artist)
                .nftSale(nftSale)
                .build();

        given(membershipUseCase.searchLatestSeasons(any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(membership),
                        PageRequest.of(0, 10), 1));

        ResultActions perform = mockMvc.perform(get("/api/v1/memberships")
                .queryParam("page", "0")
                .queryParam("size", "10")
                .queryParam("sort", "createdAt,desc")
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.content[0].title").value(membership.getTitle()));

        perform.andDo(print())
                .andDo(document("membership-list",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        queryParameters(
                                parameterWithName("page").description("페이지"),
                                parameterWithName("size").description("페이지 크기"),
                                parameterWithName("sort").description("정렬 요소,순서"))));
    }

    @Test
    @DisplayName("판매량이 가장 많은 3개의 멤버십을 조회한다.")
    void membershipListByTop3Sales() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        List<Membership> memberships = IntStream.range(0, 4)
                .mapToObj(i -> Membership.builder()
                        .id(1L)
                        .title("아이유 (IU)")
                        .description("아이유입니다.")
                        .season(1)
                        .seasonStart(now)
                        .seasonEnd(now.plusYears(1))
                        .purchaseStart(now)
                        .purchaseEnd(now.plusMonths(1))
                        .saleCount(i)
                        .thumbnailUrl("https://image.com/iu")
                        .createdAt(now)
                        .modifiedAt(now)
                        .artist(Artist.builder()
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
                                .build())
                        .nftSale(NftSale.builder()
                                .id(1L)
                                .totalNftCount(1)
                                .soldNftCount(0)
                                .totalNftAmount(10000L)
                                .soldNftAmount(0L)
                                .createdAt(now)
                                .modifiedAt(now)
                                .build())
                        .build())
                .toList();
        given(membershipUseCase.searchTop3SalesMembership())
                .willReturn(memberships);

        ResultActions perform = mockMvc.perform(get("/api/v1/memberships/best"));

        perform.andExpect(status().isOk());

        perform.andDo(print())
                .andDo(document("membership-best-list",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }

    @Test
    @DisplayName("멤버십을 수정한다.")
    void membershipModify() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        MembershipModifyRequest request = new MembershipModifyRequest(1L,
                "아이유 (IU)",
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
        NftSale nftSale = NftSale.builder()
                .id(1L)
                .totalNftCount(1)
                .soldNftCount(0)
                .totalNftAmount(10000L)
                .soldNftAmount(0L)
                .createdAt(now)
                .modifiedAt(now)
                .build();
        Membership membership = Membership.builder()
                .id(1L)
                .title("아이유 (IU)")
                .description("아이유입니다.")
                .season(1)
                .seasonStart(now)
                .seasonEnd(now.plusYears(1))
                .purchaseStart(now)
                .purchaseEnd(now.plusMonths(1))
                .saleCount(0)
                .thumbnailUrl("https://image.com/iu")
                .createdAt(now)
                .modifiedAt(now)
                .artist(artist)
                .nftSale(nftSale)
                .build();

        given(membershipUseCase.modify(any(MembershipModifyRequest.class), any(Long.class),
                any(Long.class)))
                .willReturn(membership);

        ResultActions perform = mockMvc.perform(put("/api/v1/memberships/{membershipId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.title").value(request.title()));

        perform.andDo(print())
                .andDo(document("membership-modify",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("membershipId").description("멤버십 ID"))));
    }
}