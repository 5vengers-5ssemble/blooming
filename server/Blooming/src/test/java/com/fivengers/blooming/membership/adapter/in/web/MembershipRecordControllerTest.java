package com.fivengers.blooming.membership.adapter.in.web;

import com.fivengers.blooming.artist.domain.Artist;
import com.fivengers.blooming.membership.application.port.in.MembershipRecordUseCase;
import com.fivengers.blooming.membership.domain.Membership;
import com.fivengers.blooming.membership.domain.MembershipRecord;
import com.fivengers.blooming.membership.domain.NftSale;
import com.fivengers.blooming.support.docs.RestDocsTest;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentRequest;
import static com.fivengers.blooming.support.docs.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(MembershipRecordController.class)
class MembershipRecordControllerTest extends RestDocsTest {

    @MockBean
    MembershipRecordUseCase membershipRecordUseCase;
    Membership membership;

    @BeforeEach
    void initObjects() {
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
        NftSale nftSale = NftSale.builder()
                .id(1L)
                .totalNftCount(1)
                .soldNftCount(0)
                .totalNftAmount(10000L)
                .soldNftAmount(0L)
                .createdAt(now)
                .modifiedAt(now)
                .build();
        this.membership = Membership.builder()
                .id(null)
                .title("아이유 (IU) 첫 번째 멤버십")
                .description("아이유 첫 번째 멤버십입니다.")
                .season(1)
                .seasonStart(now)
                .seasonEnd(now.plusYears(1))
                .purchaseStart(now)
                .purchaseEnd(now.plusMonths(1))
                .thumbnailUrl("https://image.com")
                .createdAt(now)
                .modifiedAt(now)
                .artist(artist)
                .nftSale(nftSale)
                .build();
    }

    @Test
    @DisplayName("최근 4주의 멤버십 구입 기록 목록을 가져온다.")
    void membershipRecordList() throws Exception {
        List<MembershipRecord> records = List.of(MembershipRecord.builder()
                        .id(1L)
                        .saleCount(2)
                        .startDateOnWeek(getThisWeekDateTime(Calendar.MONDAY, 0, 0, 0, 0, 0))
                        .endDateOnWeek(getThisWeekDateTime(Calendar.SUNDAY, 0, 23, 59, 59, 999_999_999))
                        .membership(membership)
                        .build(),
                MembershipRecord.builder()
                        .id(2L)
                        .saleCount(3)
                        .startDateOnWeek(getThisWeekDateTime(Calendar.MONDAY, 1, 0, 0, 0, 0))
                        .endDateOnWeek(getThisWeekDateTime(Calendar.SUNDAY, 1, 23, 59, 59, 999_999_999))
                        .membership(membership)
                        .build(),
                MembershipRecord.builder()
                        .id(3L)
                        .saleCount(4)
                        .startDateOnWeek(getThisWeekDateTime(Calendar.MONDAY, 2, 0, 0, 0, 0))
                        .endDateOnWeek(getThisWeekDateTime(Calendar.SUNDAY, 2, 23, 59, 59, 999_999_999))
                        .membership(membership)
                        .build(),
                MembershipRecord.builder()
                        .id(4L)
                        .saleCount(5)
                        .startDateOnWeek(getThisWeekDateTime(Calendar.MONDAY, 3, 0, 0, 0, 0))
                        .endDateOnWeek(getThisWeekDateTime(Calendar.SUNDAY, 3, 23, 59, 59, 999_999_999))
                        .membership(membership)
                        .build());
        given(membershipRecordUseCase.findOnLatestFourWeek(any(Long.class)))
                .willReturn(records);

        ResultActions perform = mockMvc.perform(
                get("/api/v1/memberships/{membershipId}/nft-records", 1L)
                        .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.results[0].startDateOnWeek").value(
                        toJson(records.get(0).getStartDateOnWeek()).replace("\"", "")))
                .andExpect(jsonPath("$.results[1].startDateOnWeek").value(
                        toJson(records.get(1).getStartDateOnWeek()).replace("\"", "")))
                .andExpect(jsonPath("$.results[2].startDateOnWeek").value(
                        toJson(records.get(2).getStartDateOnWeek()).replace("\"", "")))
                .andExpect(jsonPath("$.results[3].startDateOnWeek").value(
                        toJson(records.get(3).getStartDateOnWeek()).replace("\"", "")));

        perform.andDo(print())
                .andDo(document("membership-record-list",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("membershipId").description("멤버십 ID"))));
    }

    private LocalDateTime getThisWeekDateTime(int dayOfWeek, int prevWeek, int hour, int minute,
            int second,
            int nanoOfSecond) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        calendar.add(Calendar.DATE, 7);
        return LocalDateTime.ofInstant(calendar.getTime().toInstant(),
                        calendar.getTimeZone().toZoneId())
                .minusWeeks(1 + prevWeek)
                .toLocalDate().atTime(hour, minute, second, nanoOfSecond);
    }
}