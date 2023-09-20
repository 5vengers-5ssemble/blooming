package com.fivengers.blooming.membership.adapter.in.web;

import static com.fivengers.blooming.support.ApiDocumentUtils.getDocumentRequest;
import static com.fivengers.blooming.support.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.membership.application.port.in.MembershipUseCase;
import com.fivengers.blooming.membership.application.port.in.dto.MembershipCreateRequest;
import com.fivengers.blooming.membership.domain.Membership;
import com.fivengers.blooming.membership.domain.NftSale;
import com.fivengers.blooming.nft.domain.Nft;
import com.fivengers.blooming.support.RestDocsTest;
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
                .createdAt(now)
                .modifiedAt(now)
                .build();
        Nft nft = Nft.builder()
                .id(1L)
                .tokenId("abcdefghijklmnopqrstuvwxyz1234567890")
                .contractAddress("1234567890abcdefghijklmnopqrstuvwxyz")
                .symbol("IU")
                .createdAt(now)
                .modifiedAt(now)
                .artist(artist)
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
                .thumbnailUrl("https://image.com")
                .createdAt(now)
                .modifiedAt(now)
                .nft(nft)
                .artist(artist)
                .nftSale(nftSale)
                .build();

        given(membershipUseCase.searchLatestSeasons(any(Pageable.class)))
                .willReturn(new PageImpl<>(List.of(membership),
                        PageRequest.of(0, 10), 1));

        ResultActions perform = mockMvc.perform(get("/api/v1/memberships")
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.content[0].title").value(membership.getTitle()));

        perform.andDo(print())
                .andDo(document("membership-list",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }

    @Test
    @DisplayName("멤버십을 등록한다.")
    void membershipCreate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Artist artist = Artist.builder()
                .id(1L)
                .stageName("아이유")
                .agency("EDAM 엔터테인먼트")
                .description("아이유입니다.")
                .createdAt(now)
                .modifiedAt(now)
                .build();
        Nft nft = Nft.builder()
                .id(1L)
                .tokenId("abcdefghijklmnopqrstuvwxyz1234567890")
                .contractAddress("1234567890abcdefghijklmnopqrstuvwxyz")
                .symbol("IU")
                .createdAt(now)
                .modifiedAt(now)
                .artist(artist)
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
                .thumbnailUrl("https://image.com")
                .createdAt(now)
                .modifiedAt(now)
                .nft(nft)
                .artist(artist)
                .nftSale(nftSale)
                .build();
        MembershipCreateRequest request = new MembershipCreateRequest("아이유 (IU)",
                "아이유입니다.",
                1,
                now,
                now.plusYears(1),
                now,
                now.plusMonths(1),
                "https://image.com",
                1L,
                1L);

        given(membershipUseCase.add(any(MembershipCreateRequest.class)))
                .willReturn(membership);

        ResultActions perform = mockMvc.perform(post("/api/v1/memberships")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results.title").value(membership.getTitle()));

        perform.andDo(print())
                .andDo(document("membership-create",
                        getDocumentRequest(),
                        getDocumentResponse()));
    }
}